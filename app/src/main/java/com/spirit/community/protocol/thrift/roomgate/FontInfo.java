/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.spirit.community.protocol.thrift.roomgate;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2019-10-09")
public class FontInfo implements org.apache.thrift.TBase<FontInfo, FontInfo._Fields>, java.io.Serializable, Cloneable, Comparable<FontInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("FontInfo");

  private static final org.apache.thrift.protocol.TField NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("name", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField SIZE_FIELD_DESC = new org.apache.thrift.protocol.TField("size", org.apache.thrift.protocol.TType.I16, (short)2);
  private static final org.apache.thrift.protocol.TField BOLD_FIELD_DESC = new org.apache.thrift.protocol.TField("bold", org.apache.thrift.protocol.TType.BOOL, (short)3);
  private static final org.apache.thrift.protocol.TField ITALIC_FIELD_DESC = new org.apache.thrift.protocol.TField("italic", org.apache.thrift.protocol.TType.BOOL, (short)4);
  private static final org.apache.thrift.protocol.TField UNDERLINE_FIELD_DESC = new org.apache.thrift.protocol.TField("underline", org.apache.thrift.protocol.TType.BOOL, (short)5);
  private static final org.apache.thrift.protocol.TField STRIKEOUT_FIELD_DESC = new org.apache.thrift.protocol.TField("strikeout", org.apache.thrift.protocol.TType.BOOL, (short)6);
  private static final org.apache.thrift.protocol.TField COLOR_FIELD_DESC = new org.apache.thrift.protocol.TField("color", org.apache.thrift.protocol.TType.I32, (short)7);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new FontInfoStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new FontInfoTupleSchemeFactory();

  public java.lang.String name; // required
  public short size; // required
  public boolean bold; // required
  public boolean italic; // required
  public boolean underline; // required
  public boolean strikeout; // required
  public int color; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    NAME((short)1, "name"),
    SIZE((short)2, "size"),
    BOLD((short)3, "bold"),
    ITALIC((short)4, "italic"),
    UNDERLINE((short)5, "underline"),
    STRIKEOUT((short)6, "strikeout"),
    COLOR((short)7, "color");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // NAME
          return NAME;
        case 2: // SIZE
          return SIZE;
        case 3: // BOLD
          return BOLD;
        case 4: // ITALIC
          return ITALIC;
        case 5: // UNDERLINE
          return UNDERLINE;
        case 6: // STRIKEOUT
          return STRIKEOUT;
        case 7: // COLOR
          return COLOR;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __SIZE_ISSET_ID = 0;
  private static final int __BOLD_ISSET_ID = 1;
  private static final int __ITALIC_ISSET_ID = 2;
  private static final int __UNDERLINE_ISSET_ID = 3;
  private static final int __STRIKEOUT_ISSET_ID = 4;
  private static final int __COLOR_ISSET_ID = 5;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.NAME, new org.apache.thrift.meta_data.FieldMetaData("name", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.SIZE, new org.apache.thrift.meta_data.FieldMetaData("size", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I16)));
    tmpMap.put(_Fields.BOLD, new org.apache.thrift.meta_data.FieldMetaData("bold", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.ITALIC, new org.apache.thrift.meta_data.FieldMetaData("italic", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.UNDERLINE, new org.apache.thrift.meta_data.FieldMetaData("underline", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.STRIKEOUT, new org.apache.thrift.meta_data.FieldMetaData("strikeout", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.COLOR, new org.apache.thrift.meta_data.FieldMetaData("color", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(FontInfo.class, metaDataMap);
  }

  public FontInfo() {
  }

  public FontInfo(
    java.lang.String name,
    short size,
    boolean bold,
    boolean italic,
    boolean underline,
    boolean strikeout,
    int color)
  {
    this();
    this.name = name;
    this.size = size;
    setSizeIsSet(true);
    this.bold = bold;
    setBoldIsSet(true);
    this.italic = italic;
    setItalicIsSet(true);
    this.underline = underline;
    setUnderlineIsSet(true);
    this.strikeout = strikeout;
    setStrikeoutIsSet(true);
    this.color = color;
    setColorIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public FontInfo(FontInfo other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetName()) {
      this.name = other.name;
    }
    this.size = other.size;
    this.bold = other.bold;
    this.italic = other.italic;
    this.underline = other.underline;
    this.strikeout = other.strikeout;
    this.color = other.color;
  }

  public FontInfo deepCopy() {
    return new FontInfo(this);
  }

  @Override
  public void clear() {
    this.name = null;
    setSizeIsSet(false);
    this.size = 0;
    setBoldIsSet(false);
    this.bold = false;
    setItalicIsSet(false);
    this.italic = false;
    setUnderlineIsSet(false);
    this.underline = false;
    setStrikeoutIsSet(false);
    this.strikeout = false;
    setColorIsSet(false);
    this.color = 0;
  }

  public java.lang.String getName() {
    return this.name;
  }

  public FontInfo setName(java.lang.String name) {
    this.name = name;
    return this;
  }

  public void unsetName() {
    this.name = null;
  }

  /** Returns true if field name is set (has been assigned a value) and false otherwise */
  public boolean isSetName() {
    return this.name != null;
  }

  public void setNameIsSet(boolean value) {
    if (!value) {
      this.name = null;
    }
  }

  public short getSize() {
    return this.size;
  }

  public FontInfo setSize(short size) {
    this.size = size;
    setSizeIsSet(true);
    return this;
  }

  public void unsetSize() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __SIZE_ISSET_ID);
  }

  /** Returns true if field size is set (has been assigned a value) and false otherwise */
  public boolean isSetSize() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __SIZE_ISSET_ID);
  }

  public void setSizeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __SIZE_ISSET_ID, value);
  }

  public boolean isBold() {
    return this.bold;
  }

  public FontInfo setBold(boolean bold) {
    this.bold = bold;
    setBoldIsSet(true);
    return this;
  }

  public void unsetBold() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __BOLD_ISSET_ID);
  }

  /** Returns true if field bold is set (has been assigned a value) and false otherwise */
  public boolean isSetBold() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __BOLD_ISSET_ID);
  }

  public void setBoldIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __BOLD_ISSET_ID, value);
  }

  public boolean isItalic() {
    return this.italic;
  }

  public FontInfo setItalic(boolean italic) {
    this.italic = italic;
    setItalicIsSet(true);
    return this;
  }

  public void unsetItalic() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __ITALIC_ISSET_ID);
  }

  /** Returns true if field italic is set (has been assigned a value) and false otherwise */
  public boolean isSetItalic() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __ITALIC_ISSET_ID);
  }

  public void setItalicIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __ITALIC_ISSET_ID, value);
  }

  public boolean isUnderline() {
    return this.underline;
  }

  public FontInfo setUnderline(boolean underline) {
    this.underline = underline;
    setUnderlineIsSet(true);
    return this;
  }

  public void unsetUnderline() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __UNDERLINE_ISSET_ID);
  }

  /** Returns true if field underline is set (has been assigned a value) and false otherwise */
  public boolean isSetUnderline() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __UNDERLINE_ISSET_ID);
  }

  public void setUnderlineIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __UNDERLINE_ISSET_ID, value);
  }

  public boolean isStrikeout() {
    return this.strikeout;
  }

  public FontInfo setStrikeout(boolean strikeout) {
    this.strikeout = strikeout;
    setStrikeoutIsSet(true);
    return this;
  }

  public void unsetStrikeout() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __STRIKEOUT_ISSET_ID);
  }

  /** Returns true if field strikeout is set (has been assigned a value) and false otherwise */
  public boolean isSetStrikeout() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __STRIKEOUT_ISSET_ID);
  }

  public void setStrikeoutIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __STRIKEOUT_ISSET_ID, value);
  }

  public int getColor() {
    return this.color;
  }

  public FontInfo setColor(int color) {
    this.color = color;
    setColorIsSet(true);
    return this;
  }

  public void unsetColor() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __COLOR_ISSET_ID);
  }

  /** Returns true if field color is set (has been assigned a value) and false otherwise */
  public boolean isSetColor() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __COLOR_ISSET_ID);
  }

  public void setColorIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __COLOR_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case NAME:
      if (value == null) {
        unsetName();
      } else {
        setName((java.lang.String)value);
      }
      break;

    case SIZE:
      if (value == null) {
        unsetSize();
      } else {
        setSize((java.lang.Short)value);
      }
      break;

    case BOLD:
      if (value == null) {
        unsetBold();
      } else {
        setBold((java.lang.Boolean)value);
      }
      break;

    case ITALIC:
      if (value == null) {
        unsetItalic();
      } else {
        setItalic((java.lang.Boolean)value);
      }
      break;

    case UNDERLINE:
      if (value == null) {
        unsetUnderline();
      } else {
        setUnderline((java.lang.Boolean)value);
      }
      break;

    case STRIKEOUT:
      if (value == null) {
        unsetStrikeout();
      } else {
        setStrikeout((java.lang.Boolean)value);
      }
      break;

    case COLOR:
      if (value == null) {
        unsetColor();
      } else {
        setColor((java.lang.Integer)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case NAME:
      return getName();

    case SIZE:
      return getSize();

    case BOLD:
      return isBold();

    case ITALIC:
      return isItalic();

    case UNDERLINE:
      return isUnderline();

    case STRIKEOUT:
      return isStrikeout();

    case COLOR:
      return getColor();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case NAME:
      return isSetName();
    case SIZE:
      return isSetSize();
    case BOLD:
      return isSetBold();
    case ITALIC:
      return isSetItalic();
    case UNDERLINE:
      return isSetUnderline();
    case STRIKEOUT:
      return isSetStrikeout();
    case COLOR:
      return isSetColor();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof FontInfo)
      return this.equals((FontInfo)that);
    return false;
  }

  public boolean equals(FontInfo that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_name = true && this.isSetName();
    boolean that_present_name = true && that.isSetName();
    if (this_present_name || that_present_name) {
      if (!(this_present_name && that_present_name))
        return false;
      if (!this.name.equals(that.name))
        return false;
    }

    boolean this_present_size = true;
    boolean that_present_size = true;
    if (this_present_size || that_present_size) {
      if (!(this_present_size && that_present_size))
        return false;
      if (this.size != that.size)
        return false;
    }

    boolean this_present_bold = true;
    boolean that_present_bold = true;
    if (this_present_bold || that_present_bold) {
      if (!(this_present_bold && that_present_bold))
        return false;
      if (this.bold != that.bold)
        return false;
    }

    boolean this_present_italic = true;
    boolean that_present_italic = true;
    if (this_present_italic || that_present_italic) {
      if (!(this_present_italic && that_present_italic))
        return false;
      if (this.italic != that.italic)
        return false;
    }

    boolean this_present_underline = true;
    boolean that_present_underline = true;
    if (this_present_underline || that_present_underline) {
      if (!(this_present_underline && that_present_underline))
        return false;
      if (this.underline != that.underline)
        return false;
    }

    boolean this_present_strikeout = true;
    boolean that_present_strikeout = true;
    if (this_present_strikeout || that_present_strikeout) {
      if (!(this_present_strikeout && that_present_strikeout))
        return false;
      if (this.strikeout != that.strikeout)
        return false;
    }

    boolean this_present_color = true;
    boolean that_present_color = true;
    if (this_present_color || that_present_color) {
      if (!(this_present_color && that_present_color))
        return false;
      if (this.color != that.color)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetName()) ? 131071 : 524287);
    if (isSetName())
      hashCode = hashCode * 8191 + name.hashCode();

    hashCode = hashCode * 8191 + size;

    hashCode = hashCode * 8191 + ((bold) ? 131071 : 524287);

    hashCode = hashCode * 8191 + ((italic) ? 131071 : 524287);

    hashCode = hashCode * 8191 + ((underline) ? 131071 : 524287);

    hashCode = hashCode * 8191 + ((strikeout) ? 131071 : 524287);

    hashCode = hashCode * 8191 + color;

    return hashCode;
  }

  @Override
  public int compareTo(FontInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetName()).compareTo(other.isSetName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.name, other.name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetSize()).compareTo(other.isSetSize());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSize()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.size, other.size);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetBold()).compareTo(other.isSetBold());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBold()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.bold, other.bold);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetItalic()).compareTo(other.isSetItalic());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetItalic()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.italic, other.italic);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetUnderline()).compareTo(other.isSetUnderline());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUnderline()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.underline, other.underline);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetStrikeout()).compareTo(other.isSetStrikeout());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStrikeout()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.strikeout, other.strikeout);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetColor()).compareTo(other.isSetColor());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetColor()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.color, other.color);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("FontInfo(");
    boolean first = true;

    sb.append("name:");
    if (this.name == null) {
      sb.append("null");
    } else {
      sb.append(this.name);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("size:");
    sb.append(this.size);
    first = false;
    if (!first) sb.append(", ");
    sb.append("bold:");
    sb.append(this.bold);
    first = false;
    if (!first) sb.append(", ");
    sb.append("italic:");
    sb.append(this.italic);
    first = false;
    if (!first) sb.append(", ");
    sb.append("underline:");
    sb.append(this.underline);
    first = false;
    if (!first) sb.append(", ");
    sb.append("strikeout:");
    sb.append(this.strikeout);
    first = false;
    if (!first) sb.append(", ");
    sb.append("color:");
    sb.append(this.color);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class FontInfoStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public FontInfoStandardScheme getScheme() {
      return new FontInfoStandardScheme();
    }
  }

  private static class FontInfoStandardScheme extends org.apache.thrift.scheme.StandardScheme<FontInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, FontInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.name = iprot.readString();
              struct.setNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // SIZE
            if (schemeField.type == org.apache.thrift.protocol.TType.I16) {
              struct.size = iprot.readI16();
              struct.setSizeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // BOLD
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.bold = iprot.readBool();
              struct.setBoldIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // ITALIC
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.italic = iprot.readBool();
              struct.setItalicIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // UNDERLINE
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.underline = iprot.readBool();
              struct.setUnderlineIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // STRIKEOUT
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.strikeout = iprot.readBool();
              struct.setStrikeoutIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // COLOR
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.color = iprot.readI32();
              struct.setColorIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, FontInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.name != null) {
        oprot.writeFieldBegin(NAME_FIELD_DESC);
        oprot.writeString(struct.name);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(SIZE_FIELD_DESC);
      oprot.writeI16(struct.size);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(BOLD_FIELD_DESC);
      oprot.writeBool(struct.bold);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(ITALIC_FIELD_DESC);
      oprot.writeBool(struct.italic);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(UNDERLINE_FIELD_DESC);
      oprot.writeBool(struct.underline);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(STRIKEOUT_FIELD_DESC);
      oprot.writeBool(struct.strikeout);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(COLOR_FIELD_DESC);
      oprot.writeI32(struct.color);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class FontInfoTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public FontInfoTupleScheme getScheme() {
      return new FontInfoTupleScheme();
    }
  }

  private static class FontInfoTupleScheme extends org.apache.thrift.scheme.TupleScheme<FontInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, FontInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetName()) {
        optionals.set(0);
      }
      if (struct.isSetSize()) {
        optionals.set(1);
      }
      if (struct.isSetBold()) {
        optionals.set(2);
      }
      if (struct.isSetItalic()) {
        optionals.set(3);
      }
      if (struct.isSetUnderline()) {
        optionals.set(4);
      }
      if (struct.isSetStrikeout()) {
        optionals.set(5);
      }
      if (struct.isSetColor()) {
        optionals.set(6);
      }
      oprot.writeBitSet(optionals, 7);
      if (struct.isSetName()) {
        oprot.writeString(struct.name);
      }
      if (struct.isSetSize()) {
        oprot.writeI16(struct.size);
      }
      if (struct.isSetBold()) {
        oprot.writeBool(struct.bold);
      }
      if (struct.isSetItalic()) {
        oprot.writeBool(struct.italic);
      }
      if (struct.isSetUnderline()) {
        oprot.writeBool(struct.underline);
      }
      if (struct.isSetStrikeout()) {
        oprot.writeBool(struct.strikeout);
      }
      if (struct.isSetColor()) {
        oprot.writeI32(struct.color);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, FontInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(7);
      if (incoming.get(0)) {
        struct.name = iprot.readString();
        struct.setNameIsSet(true);
      }
      if (incoming.get(1)) {
        struct.size = iprot.readI16();
        struct.setSizeIsSet(true);
      }
      if (incoming.get(2)) {
        struct.bold = iprot.readBool();
        struct.setBoldIsSet(true);
      }
      if (incoming.get(3)) {
        struct.italic = iprot.readBool();
        struct.setItalicIsSet(true);
      }
      if (incoming.get(4)) {
        struct.underline = iprot.readBool();
        struct.setUnderlineIsSet(true);
      }
      if (incoming.get(5)) {
        struct.strikeout = iprot.readBool();
        struct.setStrikeoutIsSet(true);
      }
      if (incoming.get(6)) {
        struct.color = iprot.readI32();
        struct.setColorIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

