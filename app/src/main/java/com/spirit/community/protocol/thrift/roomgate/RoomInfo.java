/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.spirit.community.protocol.thrift.roomgate;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2019-10-09")
public class RoomInfo implements org.apache.thrift.TBase<RoomInfo, RoomInfo._Fields>, java.io.Serializable, Cloneable, Comparable<RoomInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RoomInfo");

  private static final org.apache.thrift.protocol.TField ROOM_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("room_id", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField ROOM_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("room_name", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField ROOM_RESOURCE_URL_FIELD_DESC = new org.apache.thrift.protocol.TField("room_resource_url", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField ROOM_CATEGORY_FIELD_DESC = new org.apache.thrift.protocol.TField("room_category", org.apache.thrift.protocol.TType.I32, (short)4);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new RoomInfoStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new RoomInfoTupleSchemeFactory();

  public int room_id; // required
  public java.lang.String room_name; // required
  public java.lang.String room_resource_url; // required
  public int room_category; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ROOM_ID((short)1, "room_id"),
    ROOM_NAME((short)2, "room_name"),
    ROOM_RESOURCE_URL((short)3, "room_resource_url"),
    ROOM_CATEGORY((short)4, "room_category");

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
        case 1: // ROOM_ID
          return ROOM_ID;
        case 2: // ROOM_NAME
          return ROOM_NAME;
        case 3: // ROOM_RESOURCE_URL
          return ROOM_RESOURCE_URL;
        case 4: // ROOM_CATEGORY
          return ROOM_CATEGORY;
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
  private static final int __ROOM_ID_ISSET_ID = 0;
  private static final int __ROOM_CATEGORY_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ROOM_ID, new org.apache.thrift.meta_data.FieldMetaData("room_id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.ROOM_NAME, new org.apache.thrift.meta_data.FieldMetaData("room_name", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ROOM_RESOURCE_URL, new org.apache.thrift.meta_data.FieldMetaData("room_resource_url", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ROOM_CATEGORY, new org.apache.thrift.meta_data.FieldMetaData("room_category", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RoomInfo.class, metaDataMap);
  }

  public RoomInfo() {
  }

  public RoomInfo(
    int room_id,
    java.lang.String room_name,
    java.lang.String room_resource_url,
    int room_category)
  {
    this();
    this.room_id = room_id;
    setRoom_idIsSet(true);
    this.room_name = room_name;
    this.room_resource_url = room_resource_url;
    this.room_category = room_category;
    setRoom_categoryIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RoomInfo(RoomInfo other) {
    __isset_bitfield = other.__isset_bitfield;
    this.room_id = other.room_id;
    if (other.isSetRoom_name()) {
      this.room_name = other.room_name;
    }
    if (other.isSetRoom_resource_url()) {
      this.room_resource_url = other.room_resource_url;
    }
    this.room_category = other.room_category;
  }

  public RoomInfo deepCopy() {
    return new RoomInfo(this);
  }

  @Override
  public void clear() {
    setRoom_idIsSet(false);
    this.room_id = 0;
    this.room_name = null;
    this.room_resource_url = null;
    setRoom_categoryIsSet(false);
    this.room_category = 0;
  }

  public int getRoom_id() {
    return this.room_id;
  }

  public RoomInfo setRoom_id(int room_id) {
    this.room_id = room_id;
    setRoom_idIsSet(true);
    return this;
  }

  public void unsetRoom_id() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __ROOM_ID_ISSET_ID);
  }

  /** Returns true if field room_id is set (has been assigned a value) and false otherwise */
  public boolean isSetRoom_id() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __ROOM_ID_ISSET_ID);
  }

  public void setRoom_idIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __ROOM_ID_ISSET_ID, value);
  }

  public java.lang.String getRoom_name() {
    return this.room_name;
  }

  public RoomInfo setRoom_name(java.lang.String room_name) {
    this.room_name = room_name;
    return this;
  }

  public void unsetRoom_name() {
    this.room_name = null;
  }

  /** Returns true if field room_name is set (has been assigned a value) and false otherwise */
  public boolean isSetRoom_name() {
    return this.room_name != null;
  }

  public void setRoom_nameIsSet(boolean value) {
    if (!value) {
      this.room_name = null;
    }
  }

  public java.lang.String getRoom_resource_url() {
    return this.room_resource_url;
  }

  public RoomInfo setRoom_resource_url(java.lang.String room_resource_url) {
    this.room_resource_url = room_resource_url;
    return this;
  }

  public void unsetRoom_resource_url() {
    this.room_resource_url = null;
  }

  /** Returns true if field room_resource_url is set (has been assigned a value) and false otherwise */
  public boolean isSetRoom_resource_url() {
    return this.room_resource_url != null;
  }

  public void setRoom_resource_urlIsSet(boolean value) {
    if (!value) {
      this.room_resource_url = null;
    }
  }

  public int getRoom_category() {
    return this.room_category;
  }

  public RoomInfo setRoom_category(int room_category) {
    this.room_category = room_category;
    setRoom_categoryIsSet(true);
    return this;
  }

  public void unsetRoom_category() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __ROOM_CATEGORY_ISSET_ID);
  }

  /** Returns true if field room_category is set (has been assigned a value) and false otherwise */
  public boolean isSetRoom_category() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __ROOM_CATEGORY_ISSET_ID);
  }

  public void setRoom_categoryIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __ROOM_CATEGORY_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case ROOM_ID:
      if (value == null) {
        unsetRoom_id();
      } else {
        setRoom_id((java.lang.Integer)value);
      }
      break;

    case ROOM_NAME:
      if (value == null) {
        unsetRoom_name();
      } else {
        setRoom_name((java.lang.String)value);
      }
      break;

    case ROOM_RESOURCE_URL:
      if (value == null) {
        unsetRoom_resource_url();
      } else {
        setRoom_resource_url((java.lang.String)value);
      }
      break;

    case ROOM_CATEGORY:
      if (value == null) {
        unsetRoom_category();
      } else {
        setRoom_category((java.lang.Integer)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case ROOM_ID:
      return getRoom_id();

    case ROOM_NAME:
      return getRoom_name();

    case ROOM_RESOURCE_URL:
      return getRoom_resource_url();

    case ROOM_CATEGORY:
      return getRoom_category();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case ROOM_ID:
      return isSetRoom_id();
    case ROOM_NAME:
      return isSetRoom_name();
    case ROOM_RESOURCE_URL:
      return isSetRoom_resource_url();
    case ROOM_CATEGORY:
      return isSetRoom_category();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof RoomInfo)
      return this.equals((RoomInfo)that);
    return false;
  }

  public boolean equals(RoomInfo that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_room_id = true;
    boolean that_present_room_id = true;
    if (this_present_room_id || that_present_room_id) {
      if (!(this_present_room_id && that_present_room_id))
        return false;
      if (this.room_id != that.room_id)
        return false;
    }

    boolean this_present_room_name = true && this.isSetRoom_name();
    boolean that_present_room_name = true && that.isSetRoom_name();
    if (this_present_room_name || that_present_room_name) {
      if (!(this_present_room_name && that_present_room_name))
        return false;
      if (!this.room_name.equals(that.room_name))
        return false;
    }

    boolean this_present_room_resource_url = true && this.isSetRoom_resource_url();
    boolean that_present_room_resource_url = true && that.isSetRoom_resource_url();
    if (this_present_room_resource_url || that_present_room_resource_url) {
      if (!(this_present_room_resource_url && that_present_room_resource_url))
        return false;
      if (!this.room_resource_url.equals(that.room_resource_url))
        return false;
    }

    boolean this_present_room_category = true;
    boolean that_present_room_category = true;
    if (this_present_room_category || that_present_room_category) {
      if (!(this_present_room_category && that_present_room_category))
        return false;
      if (this.room_category != that.room_category)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + room_id;

    hashCode = hashCode * 8191 + ((isSetRoom_name()) ? 131071 : 524287);
    if (isSetRoom_name())
      hashCode = hashCode * 8191 + room_name.hashCode();

    hashCode = hashCode * 8191 + ((isSetRoom_resource_url()) ? 131071 : 524287);
    if (isSetRoom_resource_url())
      hashCode = hashCode * 8191 + room_resource_url.hashCode();

    hashCode = hashCode * 8191 + room_category;

    return hashCode;
  }

  @Override
  public int compareTo(RoomInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetRoom_id()).compareTo(other.isSetRoom_id());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRoom_id()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.room_id, other.room_id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetRoom_name()).compareTo(other.isSetRoom_name());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRoom_name()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.room_name, other.room_name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetRoom_resource_url()).compareTo(other.isSetRoom_resource_url());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRoom_resource_url()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.room_resource_url, other.room_resource_url);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetRoom_category()).compareTo(other.isSetRoom_category());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRoom_category()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.room_category, other.room_category);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("RoomInfo(");
    boolean first = true;

    sb.append("room_id:");
    sb.append(this.room_id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("room_name:");
    if (this.room_name == null) {
      sb.append("null");
    } else {
      sb.append(this.room_name);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("room_resource_url:");
    if (this.room_resource_url == null) {
      sb.append("null");
    } else {
      sb.append(this.room_resource_url);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("room_category:");
    sb.append(this.room_category);
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

  private static class RoomInfoStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RoomInfoStandardScheme getScheme() {
      return new RoomInfoStandardScheme();
    }
  }

  private static class RoomInfoStandardScheme extends org.apache.thrift.scheme.StandardScheme<RoomInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RoomInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ROOM_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.room_id = iprot.readI32();
              struct.setRoom_idIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ROOM_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.room_name = iprot.readString();
              struct.setRoom_nameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // ROOM_RESOURCE_URL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.room_resource_url = iprot.readString();
              struct.setRoom_resource_urlIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // ROOM_CATEGORY
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.room_category = iprot.readI32();
              struct.setRoom_categoryIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, RoomInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ROOM_ID_FIELD_DESC);
      oprot.writeI32(struct.room_id);
      oprot.writeFieldEnd();
      if (struct.room_name != null) {
        oprot.writeFieldBegin(ROOM_NAME_FIELD_DESC);
        oprot.writeString(struct.room_name);
        oprot.writeFieldEnd();
      }
      if (struct.room_resource_url != null) {
        oprot.writeFieldBegin(ROOM_RESOURCE_URL_FIELD_DESC);
        oprot.writeString(struct.room_resource_url);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(ROOM_CATEGORY_FIELD_DESC);
      oprot.writeI32(struct.room_category);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RoomInfoTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RoomInfoTupleScheme getScheme() {
      return new RoomInfoTupleScheme();
    }
  }

  private static class RoomInfoTupleScheme extends org.apache.thrift.scheme.TupleScheme<RoomInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RoomInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetRoom_id()) {
        optionals.set(0);
      }
      if (struct.isSetRoom_name()) {
        optionals.set(1);
      }
      if (struct.isSetRoom_resource_url()) {
        optionals.set(2);
      }
      if (struct.isSetRoom_category()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetRoom_id()) {
        oprot.writeI32(struct.room_id);
      }
      if (struct.isSetRoom_name()) {
        oprot.writeString(struct.room_name);
      }
      if (struct.isSetRoom_resource_url()) {
        oprot.writeString(struct.room_resource_url);
      }
      if (struct.isSetRoom_category()) {
        oprot.writeI32(struct.room_category);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RoomInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.room_id = iprot.readI32();
        struct.setRoom_idIsSet(true);
      }
      if (incoming.get(1)) {
        struct.room_name = iprot.readString();
        struct.setRoom_nameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.room_resource_url = iprot.readString();
        struct.setRoom_resource_urlIsSet(true);
      }
      if (incoming.get(3)) {
        struct.room_category = iprot.readI32();
        struct.setRoom_categoryIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

