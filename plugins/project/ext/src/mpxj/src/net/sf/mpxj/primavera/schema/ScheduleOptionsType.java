//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.03.27 at 11:14:59 AM BST 
//

package net.sf.mpxj.primavera.schema;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>Java class for ScheduleOptionsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ScheduleOptionsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CalculateFloatBasedOnFinishDate" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ComputeTotalFloatType" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Start Float = Late Start - Early Start"/>
 *               &lt;enumeration value="Finish Float = Late Finish - Early Finish"/>
 *               &lt;enumeration value="Smallest of Start Float and Finish Float"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CreateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="CreateUser" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CriticalActivityFloatThreshold" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="CriticalActivityPathType" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Critical Float"/>
 *               &lt;enumeration value="Longest Path"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="IgnoreOtherProjectRelationships" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="LastUpdateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="LastUpdateUser" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LevelResourcesDuringScheduling" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="MakeOpenEndedActivitiesCritical" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="MaximumMultipleFloatPaths" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;minInclusive value="0"/>
 *               &lt;maxInclusive value="1000"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="MultipleFloatPathsEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="MultipleFloatPathsEndingActivityObjectId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="MultipleFloatPathsUseTotalFloat" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="OutOfSequenceScheduleType" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value=""/>
 *               &lt;enumeration value="Retained Logic"/>
 *               &lt;enumeration value="Progress Override"/>
 *               &lt;enumeration value="Actual Dates"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ProjectId" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ProjectObjectId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="RecalculateResourceCosts" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="RelationshipLagCalendar" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Predecessor Activity Calendar"/>
 *               &lt;enumeration value="Successor Activity Calendar"/>
 *               &lt;enumeration value="24 Hour Calendar"/>
 *               &lt;enumeration value="Project Default Calendar"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="StartToStartLagCalculationType" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="UseExpectedFinishDates" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="UserName" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="UserObjectId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD) @XmlType(name = "ScheduleOptionsType", propOrder =
{
   "calculateFloatBasedOnFinishDate",
   "computeTotalFloatType",
   "createDate",
   "createUser",
   "criticalActivityFloatThreshold",
   "criticalActivityPathType",
   "ignoreOtherProjectRelationships",
   "lastUpdateDate",
   "lastUpdateUser",
   "levelResourcesDuringScheduling",
   "makeOpenEndedActivitiesCritical",
   "maximumMultipleFloatPaths",
   "multipleFloatPathsEnabled",
   "multipleFloatPathsEndingActivityObjectId",
   "multipleFloatPathsUseTotalFloat",
   "outOfSequenceScheduleType",
   "projectId",
   "projectObjectId",
   "recalculateResourceCosts",
   "relationshipLagCalendar",
   "startToStartLagCalculationType",
   "useExpectedFinishDates",
   "userName",
   "userObjectId"
}) public class ScheduleOptionsType
{

   @XmlElement(name = "CalculateFloatBasedOnFinishDate", nillable = true) protected Boolean calculateFloatBasedOnFinishDate;
   @XmlElement(name = "ComputeTotalFloatType") protected String computeTotalFloatType;
   @XmlElement(name = "CreateDate", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "dateTime") protected Date createDate;
   @XmlElement(name = "CreateUser") protected String createUser;
   @XmlElement(name = "CriticalActivityFloatThreshold", nillable = true) protected Double criticalActivityFloatThreshold;
   @XmlElement(name = "CriticalActivityPathType") protected String criticalActivityPathType;
   @XmlElement(name = "IgnoreOtherProjectRelationships", nillable = true) protected Boolean ignoreOtherProjectRelationships;
   @XmlElement(name = "LastUpdateDate", type = String.class, nillable = true) @XmlJavaTypeAdapter(Adapter1.class) @XmlSchemaType(name = "dateTime") protected Date lastUpdateDate;
   @XmlElement(name = "LastUpdateUser") protected String lastUpdateUser;
   @XmlElement(name = "LevelResourcesDuringScheduling", nillable = true) protected Boolean levelResourcesDuringScheduling;
   @XmlElement(name = "MakeOpenEndedActivitiesCritical", nillable = true) protected Boolean makeOpenEndedActivitiesCritical;
   @XmlElement(name = "MaximumMultipleFloatPaths", nillable = true) protected Integer maximumMultipleFloatPaths;
   @XmlElement(name = "MultipleFloatPathsEnabled", nillable = true) protected Boolean multipleFloatPathsEnabled;
   @XmlElement(name = "MultipleFloatPathsEndingActivityObjectId", nillable = true) protected Integer multipleFloatPathsEndingActivityObjectId;
   @XmlElement(name = "MultipleFloatPathsUseTotalFloat", nillable = true) protected Boolean multipleFloatPathsUseTotalFloat;
   @XmlElement(name = "OutOfSequenceScheduleType") protected String outOfSequenceScheduleType;
   @XmlElement(name = "ProjectId") protected String projectId;
   @XmlElement(name = "ProjectObjectId", nillable = true) protected Integer projectObjectId;
   @XmlElement(name = "RecalculateResourceCosts", nillable = true) protected Boolean recalculateResourceCosts;
   @XmlElement(name = "RelationshipLagCalendar") protected String relationshipLagCalendar;
   @XmlElement(name = "StartToStartLagCalculationType", nillable = true) protected Boolean startToStartLagCalculationType;
   @XmlElement(name = "UseExpectedFinishDates", nillable = true) protected Boolean useExpectedFinishDates;
   @XmlElement(name = "UserName") protected String userName;
   @XmlElement(name = "UserObjectId", nillable = true) protected Integer userObjectId;

   /**
    * Gets the value of the calculateFloatBasedOnFinishDate property.
    * 
    * @return
    *     possible object is
    *     {@link Boolean }
    *     
    */
   public Boolean isCalculateFloatBasedOnFinishDate()
   {
      return calculateFloatBasedOnFinishDate;
   }

   /**
    * Sets the value of the calculateFloatBasedOnFinishDate property.
    * 
    * @param value
    *     allowed object is
    *     {@link Boolean }
    *     
    */
   public void setCalculateFloatBasedOnFinishDate(Boolean value)
   {
      this.calculateFloatBasedOnFinishDate = value;
   }

   /**
    * Gets the value of the computeTotalFloatType property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public String getComputeTotalFloatType()
   {
      return computeTotalFloatType;
   }

   /**
    * Sets the value of the computeTotalFloatType property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setComputeTotalFloatType(String value)
   {
      this.computeTotalFloatType = value;
   }

   /**
    * Gets the value of the createDate property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Date getCreateDate()
   {
      return createDate;
   }

   /**
    * Sets the value of the createDate property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setCreateDate(Date value)
   {
      this.createDate = value;
   }

   /**
    * Gets the value of the createUser property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public String getCreateUser()
   {
      return createUser;
   }

   /**
    * Sets the value of the createUser property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setCreateUser(String value)
   {
      this.createUser = value;
   }

   /**
    * Gets the value of the criticalActivityFloatThreshold property.
    * 
    * @return
    *     possible object is
    *     {@link Double }
    *     
    */
   public Double getCriticalActivityFloatThreshold()
   {
      return criticalActivityFloatThreshold;
   }

   /**
    * Sets the value of the criticalActivityFloatThreshold property.
    * 
    * @param value
    *     allowed object is
    *     {@link Double }
    *     
    */
   public void setCriticalActivityFloatThreshold(Double value)
   {
      this.criticalActivityFloatThreshold = value;
   }

   /**
    * Gets the value of the criticalActivityPathType property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public String getCriticalActivityPathType()
   {
      return criticalActivityPathType;
   }

   /**
    * Sets the value of the criticalActivityPathType property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setCriticalActivityPathType(String value)
   {
      this.criticalActivityPathType = value;
   }

   /**
    * Gets the value of the ignoreOtherProjectRelationships property.
    * 
    * @return
    *     possible object is
    *     {@link Boolean }
    *     
    */
   public Boolean isIgnoreOtherProjectRelationships()
   {
      return ignoreOtherProjectRelationships;
   }

   /**
    * Sets the value of the ignoreOtherProjectRelationships property.
    * 
    * @param value
    *     allowed object is
    *     {@link Boolean }
    *     
    */
   public void setIgnoreOtherProjectRelationships(Boolean value)
   {
      this.ignoreOtherProjectRelationships = value;
   }

   /**
    * Gets the value of the lastUpdateDate property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public Date getLastUpdateDate()
   {
      return lastUpdateDate;
   }

   /**
    * Sets the value of the lastUpdateDate property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setLastUpdateDate(Date value)
   {
      this.lastUpdateDate = value;
   }

   /**
    * Gets the value of the lastUpdateUser property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public String getLastUpdateUser()
   {
      return lastUpdateUser;
   }

   /**
    * Sets the value of the lastUpdateUser property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setLastUpdateUser(String value)
   {
      this.lastUpdateUser = value;
   }

   /**
    * Gets the value of the levelResourcesDuringScheduling property.
    * 
    * @return
    *     possible object is
    *     {@link Boolean }
    *     
    */
   public Boolean isLevelResourcesDuringScheduling()
   {
      return levelResourcesDuringScheduling;
   }

   /**
    * Sets the value of the levelResourcesDuringScheduling property.
    * 
    * @param value
    *     allowed object is
    *     {@link Boolean }
    *     
    */
   public void setLevelResourcesDuringScheduling(Boolean value)
   {
      this.levelResourcesDuringScheduling = value;
   }

   /**
    * Gets the value of the makeOpenEndedActivitiesCritical property.
    * 
    * @return
    *     possible object is
    *     {@link Boolean }
    *     
    */
   public Boolean isMakeOpenEndedActivitiesCritical()
   {
      return makeOpenEndedActivitiesCritical;
   }

   /**
    * Sets the value of the makeOpenEndedActivitiesCritical property.
    * 
    * @param value
    *     allowed object is
    *     {@link Boolean }
    *     
    */
   public void setMakeOpenEndedActivitiesCritical(Boolean value)
   {
      this.makeOpenEndedActivitiesCritical = value;
   }

   /**
    * Gets the value of the maximumMultipleFloatPaths property.
    * 
    * @return
    *     possible object is
    *     {@link Integer }
    *     
    */
   public Integer getMaximumMultipleFloatPaths()
   {
      return maximumMultipleFloatPaths;
   }

   /**
    * Sets the value of the maximumMultipleFloatPaths property.
    * 
    * @param value
    *     allowed object is
    *     {@link Integer }
    *     
    */
   public void setMaximumMultipleFloatPaths(Integer value)
   {
      this.maximumMultipleFloatPaths = value;
   }

   /**
    * Gets the value of the multipleFloatPathsEnabled property.
    * 
    * @return
    *     possible object is
    *     {@link Boolean }
    *     
    */
   public Boolean isMultipleFloatPathsEnabled()
   {
      return multipleFloatPathsEnabled;
   }

   /**
    * Sets the value of the multipleFloatPathsEnabled property.
    * 
    * @param value
    *     allowed object is
    *     {@link Boolean }
    *     
    */
   public void setMultipleFloatPathsEnabled(Boolean value)
   {
      this.multipleFloatPathsEnabled = value;
   }

   /**
    * Gets the value of the multipleFloatPathsEndingActivityObjectId property.
    * 
    * @return
    *     possible object is
    *     {@link Integer }
    *     
    */
   public Integer getMultipleFloatPathsEndingActivityObjectId()
   {
      return multipleFloatPathsEndingActivityObjectId;
   }

   /**
    * Sets the value of the multipleFloatPathsEndingActivityObjectId property.
    * 
    * @param value
    *     allowed object is
    *     {@link Integer }
    *     
    */
   public void setMultipleFloatPathsEndingActivityObjectId(Integer value)
   {
      this.multipleFloatPathsEndingActivityObjectId = value;
   }

   /**
    * Gets the value of the multipleFloatPathsUseTotalFloat property.
    * 
    * @return
    *     possible object is
    *     {@link Boolean }
    *     
    */
   public Boolean isMultipleFloatPathsUseTotalFloat()
   {
      return multipleFloatPathsUseTotalFloat;
   }

   /**
    * Sets the value of the multipleFloatPathsUseTotalFloat property.
    * 
    * @param value
    *     allowed object is
    *     {@link Boolean }
    *     
    */
   public void setMultipleFloatPathsUseTotalFloat(Boolean value)
   {
      this.multipleFloatPathsUseTotalFloat = value;
   }

   /**
    * Gets the value of the outOfSequenceScheduleType property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public String getOutOfSequenceScheduleType()
   {
      return outOfSequenceScheduleType;
   }

   /**
    * Sets the value of the outOfSequenceScheduleType property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setOutOfSequenceScheduleType(String value)
   {
      this.outOfSequenceScheduleType = value;
   }

   /**
    * Gets the value of the projectId property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public String getProjectId()
   {
      return projectId;
   }

   /**
    * Sets the value of the projectId property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setProjectId(String value)
   {
      this.projectId = value;
   }

   /**
    * Gets the value of the projectObjectId property.
    * 
    * @return
    *     possible object is
    *     {@link Integer }
    *     
    */
   public Integer getProjectObjectId()
   {
      return projectObjectId;
   }

   /**
    * Sets the value of the projectObjectId property.
    * 
    * @param value
    *     allowed object is
    *     {@link Integer }
    *     
    */
   public void setProjectObjectId(Integer value)
   {
      this.projectObjectId = value;
   }

   /**
    * Gets the value of the recalculateResourceCosts property.
    * 
    * @return
    *     possible object is
    *     {@link Boolean }
    *     
    */
   public Boolean isRecalculateResourceCosts()
   {
      return recalculateResourceCosts;
   }

   /**
    * Sets the value of the recalculateResourceCosts property.
    * 
    * @param value
    *     allowed object is
    *     {@link Boolean }
    *     
    */
   public void setRecalculateResourceCosts(Boolean value)
   {
      this.recalculateResourceCosts = value;
   }

   /**
    * Gets the value of the relationshipLagCalendar property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public String getRelationshipLagCalendar()
   {
      return relationshipLagCalendar;
   }

   /**
    * Sets the value of the relationshipLagCalendar property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setRelationshipLagCalendar(String value)
   {
      this.relationshipLagCalendar = value;
   }

   /**
    * Gets the value of the startToStartLagCalculationType property.
    * 
    * @return
    *     possible object is
    *     {@link Boolean }
    *     
    */
   public Boolean isStartToStartLagCalculationType()
   {
      return startToStartLagCalculationType;
   }

   /**
    * Sets the value of the startToStartLagCalculationType property.
    * 
    * @param value
    *     allowed object is
    *     {@link Boolean }
    *     
    */
   public void setStartToStartLagCalculationType(Boolean value)
   {
      this.startToStartLagCalculationType = value;
   }

   /**
    * Gets the value of the useExpectedFinishDates property.
    * 
    * @return
    *     possible object is
    *     {@link Boolean }
    *     
    */
   public Boolean isUseExpectedFinishDates()
   {
      return useExpectedFinishDates;
   }

   /**
    * Sets the value of the useExpectedFinishDates property.
    * 
    * @param value
    *     allowed object is
    *     {@link Boolean }
    *     
    */
   public void setUseExpectedFinishDates(Boolean value)
   {
      this.useExpectedFinishDates = value;
   }

   /**
    * Gets the value of the userName property.
    * 
    * @return
    *     possible object is
    *     {@link String }
    *     
    */
   public String getUserName()
   {
      return userName;
   }

   /**
    * Sets the value of the userName property.
    * 
    * @param value
    *     allowed object is
    *     {@link String }
    *     
    */
   public void setUserName(String value)
   {
      this.userName = value;
   }

   /**
    * Gets the value of the userObjectId property.
    * 
    * @return
    *     possible object is
    *     {@link Integer }
    *     
    */
   public Integer getUserObjectId()
   {
      return userObjectId;
   }

   /**
    * Sets the value of the userObjectId property.
    * 
    * @param value
    *     allowed object is
    *     {@link Integer }
    *     
    */
   public void setUserObjectId(Integer value)
   {
      this.userObjectId = value;
   }

}
