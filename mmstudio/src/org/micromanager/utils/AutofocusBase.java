package org.micromanager.utils;

import java.util.ArrayList;
import java.util.prefs.Preferences;

import org.micromanager.api.Autofocus;

public abstract class AutofocusBase implements Autofocus{
   //private Hashtable<String, PropertyItem> properties_;
   private ArrayList<PropertyItem> properties_;
   Preferences prefs_;
   private static final String AF_UNIMPLEMENTED_FUNCTION = "Operation not supported.";

   public AutofocusBase() {
      Preferences root = Preferences.userNodeForPackage(this.getClass());
      prefs_ = root.node(root.absolutePath() + "/settings");
      properties_ = new ArrayList<PropertyItem>();
   }

   protected void createProperty(String name) {
      PropertyItem p = new PropertyItem();
      p.name = name;
      p.device = getDeviceName();
      properties_.add(p);
   }
   protected void createProperty(String name, String value) {
      PropertyItem p = new PropertyItem();
      p.name = name;
      p.value = value;
      p.device = getDeviceName();
      properties_.add(p);
   }

   protected void createProperty(String name, String value, String[] allowed) {
      PropertyItem p = new PropertyItem();
      p.allowed = allowed;
      p.name = name;
      p.value = value;
      p.device = getDeviceName();
      properties_.add(p);
   }

   /**
    * Add property to the list of device properties.
    * This is the most general method where all property features can be controlled.
    * @param p - property object
    */
   protected void addProperty(PropertyItem p) {
      properties_.add(p);
   }

   /**
    * Get all property names (keys)
    * @return - an array of property names
    * @throws MMException
    */
   public String[] getPropertyNames() {
      String[] propName = new String[properties_.size()];
      for (int i=0; i<properties_.size(); i++) {
         propName[i] = properties_.get(i).name;
      }
      return propName;

      //return properties_.keySet().toArray(new String[0]);
      
   }

   /**
    * Get value for a given property name.
    */
   public String getPropertyValue(String name) throws MMException {
      for (int i=0; i<properties_.size(); i++) {
         if (name.equals(properties_.get(i).name)) {
            return properties_.get(i).value;
         }
      }
      throw new MMException("Unknown property: " + name);
   }

   /**
    * Get property for a given property name.
    */
   public PropertyItem getProperty(String name) throws MMException {
      for (int i=0; i<properties_.size(); i++) {
         if (name.equals(properties_.get(i).name)) {
            return properties_.get(i);
         }
      }
      throw new MMException("Unknown property: " + name);

   }

   public void setProperty(PropertyItem p) throws MMException {
      for (int i=0; i<properties_.size(); i++) {
         if (p.name.equals(properties_.get(i).name)) {
            properties_.set(i, p);
            return;
         } 
      }
      properties_.add(p);
   }

   /**
    * Sets value for a given property name.
    * This method will not check if the value is allowed or not,
    * or whether it conforms to the property limits.
    * It is assumed that the caller will take care of that using appropriate
    * property information (see Property class)
    */
   public void setPropertyValue(String name, String value) throws MMException {
      for (int i=0; i<properties_.size(); i++) {
         if (name.equals(properties_.get(i).name)) {
            properties_.get(i).value = value;
            return;
         }
      }
      throw new MMException("Unknown property: " + name);
   }

   public PropertyItem[] getProperties() {
      return properties_.toArray(new PropertyItem[properties_.size()]);
   }

   public void saveSettings() {
      for (int i=0; i<properties_.size(); i++) {
         prefs_.put(properties_.get(i).name, properties_.get(i).value);
      }      
   }

   public void loadSettings() {
      for (int i=0; i<properties_.size(); i++) {
         properties_.get(i).value = prefs_.get(properties_.get(i).name, properties_.get(i).value);
      }      
   }

   public void dumpProperties(String msg) {
      ReportingUtils.logMessage(msg);
      for (int i=0; i<properties_.size(); i++) {
         properties_.get(i).dump();
      }
         /*
      for (PropertyItem pi : (PropertyItem) properties_.toArray()) {
         pi.dump();
      }
      */
   }

   public void enableContinuousFocus(boolean enable) throws MMException {
      throw new MMException(AF_UNIMPLEMENTED_FUNCTION);
   }

   public boolean isContinuousFocusEnabled() throws MMException {
      return false;
   }

   public boolean isContinuousFocusLocked() throws MMException {
      return false;
   }


}
