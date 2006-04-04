package au.gov.naa.digipres.xena.plugin.email;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.xml.sax.SAXException;

import au.gov.naa.digipres.xena.gui.InternalFrame;
import au.gov.naa.digipres.xena.gui.MainFrame;
import au.gov.naa.digipres.xena.helper.JdomUtil;
import au.gov.naa.digipres.xena.helper.JdomXenaView;
import au.gov.naa.digipres.xena.kernel.XenaException;
import au.gov.naa.digipres.xena.kernel.view.XenaView;

/**
 * View to display an email. Body is displayed at the top and attachments
 * in a list at the bottom.
 *
 * @author Chris Bitmead
 */
public class EmailView extends JdomXenaView {

	private BorderLayout borderLayout1 = new BorderLayout();

	private JSplitPane splitPane1 = new JSplitPane();

	private JSplitPane splitPane2 = new JSplitPane();

	private JPanel headerPanel = new JPanel();

	protected JScrollPane headerScroll = new JScrollPane();

	private BorderLayout borderLayout2 = new BorderLayout();

	private JPanel namePanel = new JPanel();

	private JPanel valuePanel = new JPanel();

	private GridLayout gridLayout1 = new GridLayout();

	private GridLayout gridLayout2 = new GridLayout();

	private JPanel bodyPanel = new JPanel();

	private JPanel attAreaPanel = new JPanel();

	private JPanel attListPanel = new JPanel();

	private JButton openButton = new JButton("Open");

	private BorderLayout borderLayout3 = new BorderLayout();

	private BorderLayout borderLayout4 = new BorderLayout();

	private BorderLayout borderLayout6 = new BorderLayout();

	protected JScrollPane attScrollPane = new JScrollPane();

	private JList attList = new JList();

	private JPanel attPanel = new JPanel();

//	private JList headerList = new JList();

	protected DefaultListModel attModel = new DefaultListModel();

	protected java.util.List attachments = new ArrayList();

	private BorderLayout borderLayout5 = new BorderLayout();

	public static void main(String[] args) {
		new EmailView();
	}

	public EmailView() {
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean canShowTag(String tag) throws XenaException {
		return tag.equals(viewManager.getPluginManager().getTypeManager().lookupXenaFileType(XenaEmailFileType.class).getTag());
	}

	public String getViewName() {
		return "Email";
	}

	public void updateViewFromElement() throws XenaException {
		final Namespace ns = Namespace.getNamespace(MessageNormaliser.PREFIX, MessageNormaliser.URI);
		Element email = this.getElement();
		Element headers = email.getChild("headers", ns);
		java.util.List headeritems = new ArrayList((java.util.List)headers.getChildren("header", ns));
		Collections.sort(headeritems, new Comparator() {
			public int compare(Object o1, Object o2) {
				String[] order = {
					"from", "to", "subject", "cc", "bcc", "date"};
				int ind1 = Integer.MAX_VALUE, ind2 = Integer.MAX_VALUE;
				Element e1 = (Element)o1;
				Element e2 = (Element)o2;
				// reinstate these lines when moving to Java 1.5.0
//				String n1 = e1.getAttributeValue("name", ns).toLowerCase();
//				String n2 = e2.getAttributeValue("name", ns).toLowerCase();
				String n1 = e1.getAttributeValue("name").toLowerCase();
				String n2 = e2.getAttributeValue("name").toLowerCase();
				for (int i = 0; i < order.length; i++) {
					if (n1.equals(order[i])) {
						ind1 = i;
					}
					if (n2.equals(order[i])) {
						ind2 = i;
					}
				}
				return ind1 - ind2;
			}

			public boolean equals(Object obj) {
				return true;
			}
		});
		Iterator it = headeritems.iterator();
		JLabel aLabel = null;
		while (it.hasNext()) {
			Element header = (Element)it.next();
			// reinstate this line when moving to Java 1.5.0
//			String name = header.getAttributeValue("name", ns);
			String name = header.getAttributeValue("name");
			namePanel.add(aLabel = new JLabel(name));
			String txt = header.getText();
			if (name.equals("Date") || name.equals("Received-Date")) {
				if (txt.charAt(txt.length() - 1) == 'Z') {
					txt = txt.substring(0, txt.length() - 1) + "+0000";
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmssZ");
				try {
					Date date = sdf.parse(txt);
					sdf = new SimpleDateFormat("dd MMM yyyy HH':'mm':'ss Z");
					Pattern pat = Pattern.compile(".*([+-][0-9]{4})");
					Matcher mat = pat.matcher(txt);
					if (mat.matches()) {
						TimeZone tz = TimeZone.getTimeZone("GMT" + mat.group(1));
						sdf.setTimeZone(tz);
					}
					txt = sdf.format(date);
				} catch (ParseException ex) {
					// Do nothing, use plain text
				}
			}
			valuePanel.add(new JLabel(txt));
		}
		Element parts = email.getChild("parts", ns);
		java.util.List partList = parts.getChildren("part", ns);
		if (1 < partList.size()) {
			splitPane1.add(splitPane2, JSplitPane.BOTTOM);
			splitPane2.add(bodyPanel, JSplitPane.TOP);
		} else {
			splitPane1.add(bodyPanel, JSplitPane.BOTTOM);
		}
		Element body = (Element)((Element)partList.get(0)).getChildren().get(0);
		XenaView view = viewManager.getDefaultView(body.getQualifiedName(), XenaView.REGULAR_VIEW, getLevel() + 1);
		try {
			JdomUtil.writeDocument(view.getContentHandler(), body);
			view.parse();
		} catch (JDOMException x) {
			throw new XenaException(x);
		} catch (SAXException x) {
			throw new XenaException(x);
		} catch (IOException x) {
			throw new XenaException(x);
		}

		setSubView(bodyPanel, view);
		if (1 < partList.size()) {
			it = partList.iterator();
			it.next();
			Element npart = (Element)it.next();
			body = (Element)npart.getChildren().get(0);
			view = viewManager.getDefaultView(body.getQualifiedName(), XenaView.REGULAR_VIEW, getLevel() + 1);

			try {
				view.setInternalFrame(getInternalFrame());
				JdomUtil.writeDocument(view.getContentHandler(), body);
				view.parse();
			} catch (JDOMException x) {
				throw new XenaException(x);
			} catch (SAXException x) {
				throw new XenaException(x);
			} catch (IOException x) {
				throw new XenaException(x);
			}
			setSubView(attPanel, view);
		}
		it = partList.iterator();
		it.next();
		for (int i = 0; it.hasNext(); i++) {
			Element npart = (Element)it.next();
			attachments.add(npart);
			String name = npart.getAttributeValue("filename", ns);
			if (name == null) {
				name = "Att-" + Integer.toString(i);
			}
			attModel.addElement(name);
		}
		attList.setSelectedIndex(0);
	}

	void jbInit() throws Exception {
		this.setLayout(borderLayout1);
		valuePanel.setLayout(gridLayout1);
		namePanel.setLayout(gridLayout2);
		gridLayout2.setColumns(1);
		gridLayout2.setRows(0);
		gridLayout1.setColumns(1);
		gridLayout1.setRows(0);
		headerPanel.setLayout(borderLayout2);
		namePanel.setBorder(BorderFactory.createEtchedBorder());
		valuePanel.setBorder(BorderFactory.createEtchedBorder());
		bodyPanel.setLayout(borderLayout3);
		attAreaPanel.setLayout(borderLayout4);
		headerScroll.setMinimumSize(new Dimension(66, 66));
		attAreaPanel.setMinimumSize(new Dimension(66, 66));
		splitPane2.setResizeWeight(1.0);
		attPanel.setLayout(borderLayout5);
		attListPanel.setLayout(borderLayout6);
		openButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openButton_actionPerformed(e);
			}
		});
		headerScroll.getViewport().add(headerPanel);
		headerPanel.add(namePanel, BorderLayout.WEST);
		headerPanel.add(valuePanel, BorderLayout.CENTER);
		splitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.add(splitPane1, BorderLayout.CENTER);
		splitPane1.add(headerScroll, JSplitPane.TOP);

		attList.setModel(attModel);
		attScrollPane.getViewport().add(attList);
		attAreaPanel.add(attListPanel, BorderLayout.WEST);
		attListPanel.add(attScrollPane, BorderLayout.CENTER);
		attListPanel.add(openButton, BorderLayout.NORTH);
		attAreaPanel.add(attPanel, BorderLayout.CENTER);
		splitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane2.add(attAreaPanel, JSplitPane.BOTTOM);
		attList.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				try {
					int index = attList.locationToIndex(e.getPoint());
					Element npart = (Element)attachments.get(index);
					Element attEl = (Element)npart.getChildren().get(0);
					XenaView view = viewManager.getDefaultView(attEl.getQualifiedName(), XenaView.REGULAR_VIEW, getLevel() + 1);

					try {
						JdomUtil.writeDocument(view.getContentHandler(), attEl);
						view.parse();
					} catch (JDOMException x) {
						throw new XenaException(x);
					} catch (SAXException x) {
						throw new XenaException(x);
					} catch (IOException x) {
						throw new XenaException(x);
					}
					setSubView(attPanel, view);
					attAreaPanel.updateUI();
				} catch (XenaException x) {
					MainFrame.singleton().showError(x);
				}
			}

			public void mousePressed(MouseEvent e) {}

			public void mouseReleased(MouseEvent e) {}

			public void mouseEntered(MouseEvent e) {}

			public void mouseExited(MouseEvent e) {}

		});

	}

	void openButton_actionPerformed(ActionEvent e) {
		try {
			int index = attList.getSelectedIndex();
			Element npart = (Element)attachments.get(index);
			Element attEl = (Element)npart.getChildren().get(0);
			/*			XenaView view = ViewManager.singleton().getDefaultView(attEl.getQualifiedName(), XenaView.REGULAR_VIEW, 0);
			   xena.gui.MainFrame.singleton().newFrame(getInternalFrame().getSavedFile(), Integer.toString(index), view, null, null); */
			final XenaView view = viewManager.getDefaultView(attEl.getQualifiedName(), XenaView.REGULAR_VIEW, 0);
			org.xml.sax.ContentHandler ch = view.getTmpFileContentHandler();
			JdomUtil.writeDocument(ch, attEl);
			view.closeContentHandler();
			InternalFrame nifr = MainFrame.singleton().showXena(view.getTmpFile().getFile(), null, view);
		} catch (XenaException x) {
			MainFrame.singleton().showError(x);
		} catch (SAXException x) {
			MainFrame.singleton().showError(x);
		} catch (JDOMException x) {
			MainFrame.singleton().showError(x);

		}
	}
}
