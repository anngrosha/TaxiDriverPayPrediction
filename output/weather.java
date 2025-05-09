// ORM class for table 'weather'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Fri May 09 01:00:52 MSK 2025
// For connector: org.apache.sqoop.manager.PostgresqlManager
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import org.apache.sqoop.lib.JdbcWritableBridge;
import org.apache.sqoop.lib.DelimiterSet;
import org.apache.sqoop.lib.FieldFormatter;
import org.apache.sqoop.lib.RecordParser;
import org.apache.sqoop.lib.BooleanParser;
import org.apache.sqoop.lib.BlobRef;
import org.apache.sqoop.lib.ClobRef;
import org.apache.sqoop.lib.LargeObjectLoader;
import org.apache.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class weather extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("station_name", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weather.this.station_name = (String)value;
      }
    });
    setters.put("station_address", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weather.this.station_address = (String)value;
      }
    });
    setters.put("resolved_address", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weather.this.resolved_address = (String)value;
      }
    });
    setters.put("date_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weather.this.date_id = (java.sql.Date)value;
      }
    });
    setters.put("temperature", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weather.this.temperature = (Double)value;
      }
    });
    setters.put("feels_like", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weather.this.feels_like = (Double)value;
      }
    });
    setters.put("dew_point", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weather.this.dew_point = (Double)value;
      }
    });
    setters.put("humidity", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weather.this.humidity = (Double)value;
      }
    });
    setters.put("precipitation", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weather.this.precipitation = (Double)value;
      }
    });
    setters.put("precipitation_prob", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weather.this.precipitation_prob = (Integer)value;
      }
    });
    setters.put("precipitation_type", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weather.this.precipitation_type = (String)value;
      }
    });
    setters.put("snow", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weather.this.snow = (Double)value;
      }
    });
    setters.put("snow_depth", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weather.this.snow_depth = (Double)value;
      }
    });
    setters.put("wind_gust", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weather.this.wind_gust = (Double)value;
      }
    });
    setters.put("wind_speed", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weather.this.wind_speed = (Double)value;
      }
    });
    setters.put("wind_direction", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weather.this.wind_direction = (Double)value;
      }
    });
    setters.put("sea_level_pressure", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weather.this.sea_level_pressure = (Double)value;
      }
    });
    setters.put("cloud_cover", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weather.this.cloud_cover = (Double)value;
      }
    });
    setters.put("visibility", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weather.this.visibility = (Double)value;
      }
    });
    setters.put("uv_index", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weather.this.uv_index = (Integer)value;
      }
    });
    setters.put("severe_risk", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        weather.this.severe_risk = (Double)value;
      }
    });
  }
  public weather() {
    init0();
  }
  private String station_name;
  public String get_station_name() {
    return station_name;
  }
  public void set_station_name(String station_name) {
    this.station_name = station_name;
  }
  public weather with_station_name(String station_name) {
    this.station_name = station_name;
    return this;
  }
  private String station_address;
  public String get_station_address() {
    return station_address;
  }
  public void set_station_address(String station_address) {
    this.station_address = station_address;
  }
  public weather with_station_address(String station_address) {
    this.station_address = station_address;
    return this;
  }
  private String resolved_address;
  public String get_resolved_address() {
    return resolved_address;
  }
  public void set_resolved_address(String resolved_address) {
    this.resolved_address = resolved_address;
  }
  public weather with_resolved_address(String resolved_address) {
    this.resolved_address = resolved_address;
    return this;
  }
  private java.sql.Date date_id;
  public java.sql.Date get_date_id() {
    return date_id;
  }
  public void set_date_id(java.sql.Date date_id) {
    this.date_id = date_id;
  }
  public weather with_date_id(java.sql.Date date_id) {
    this.date_id = date_id;
    return this;
  }
  private Double temperature;
  public Double get_temperature() {
    return temperature;
  }
  public void set_temperature(Double temperature) {
    this.temperature = temperature;
  }
  public weather with_temperature(Double temperature) {
    this.temperature = temperature;
    return this;
  }
  private Double feels_like;
  public Double get_feels_like() {
    return feels_like;
  }
  public void set_feels_like(Double feels_like) {
    this.feels_like = feels_like;
  }
  public weather with_feels_like(Double feels_like) {
    this.feels_like = feels_like;
    return this;
  }
  private Double dew_point;
  public Double get_dew_point() {
    return dew_point;
  }
  public void set_dew_point(Double dew_point) {
    this.dew_point = dew_point;
  }
  public weather with_dew_point(Double dew_point) {
    this.dew_point = dew_point;
    return this;
  }
  private Double humidity;
  public Double get_humidity() {
    return humidity;
  }
  public void set_humidity(Double humidity) {
    this.humidity = humidity;
  }
  public weather with_humidity(Double humidity) {
    this.humidity = humidity;
    return this;
  }
  private Double precipitation;
  public Double get_precipitation() {
    return precipitation;
  }
  public void set_precipitation(Double precipitation) {
    this.precipitation = precipitation;
  }
  public weather with_precipitation(Double precipitation) {
    this.precipitation = precipitation;
    return this;
  }
  private Integer precipitation_prob;
  public Integer get_precipitation_prob() {
    return precipitation_prob;
  }
  public void set_precipitation_prob(Integer precipitation_prob) {
    this.precipitation_prob = precipitation_prob;
  }
  public weather with_precipitation_prob(Integer precipitation_prob) {
    this.precipitation_prob = precipitation_prob;
    return this;
  }
  private String precipitation_type;
  public String get_precipitation_type() {
    return precipitation_type;
  }
  public void set_precipitation_type(String precipitation_type) {
    this.precipitation_type = precipitation_type;
  }
  public weather with_precipitation_type(String precipitation_type) {
    this.precipitation_type = precipitation_type;
    return this;
  }
  private Double snow;
  public Double get_snow() {
    return snow;
  }
  public void set_snow(Double snow) {
    this.snow = snow;
  }
  public weather with_snow(Double snow) {
    this.snow = snow;
    return this;
  }
  private Double snow_depth;
  public Double get_snow_depth() {
    return snow_depth;
  }
  public void set_snow_depth(Double snow_depth) {
    this.snow_depth = snow_depth;
  }
  public weather with_snow_depth(Double snow_depth) {
    this.snow_depth = snow_depth;
    return this;
  }
  private Double wind_gust;
  public Double get_wind_gust() {
    return wind_gust;
  }
  public void set_wind_gust(Double wind_gust) {
    this.wind_gust = wind_gust;
  }
  public weather with_wind_gust(Double wind_gust) {
    this.wind_gust = wind_gust;
    return this;
  }
  private Double wind_speed;
  public Double get_wind_speed() {
    return wind_speed;
  }
  public void set_wind_speed(Double wind_speed) {
    this.wind_speed = wind_speed;
  }
  public weather with_wind_speed(Double wind_speed) {
    this.wind_speed = wind_speed;
    return this;
  }
  private Double wind_direction;
  public Double get_wind_direction() {
    return wind_direction;
  }
  public void set_wind_direction(Double wind_direction) {
    this.wind_direction = wind_direction;
  }
  public weather with_wind_direction(Double wind_direction) {
    this.wind_direction = wind_direction;
    return this;
  }
  private Double sea_level_pressure;
  public Double get_sea_level_pressure() {
    return sea_level_pressure;
  }
  public void set_sea_level_pressure(Double sea_level_pressure) {
    this.sea_level_pressure = sea_level_pressure;
  }
  public weather with_sea_level_pressure(Double sea_level_pressure) {
    this.sea_level_pressure = sea_level_pressure;
    return this;
  }
  private Double cloud_cover;
  public Double get_cloud_cover() {
    return cloud_cover;
  }
  public void set_cloud_cover(Double cloud_cover) {
    this.cloud_cover = cloud_cover;
  }
  public weather with_cloud_cover(Double cloud_cover) {
    this.cloud_cover = cloud_cover;
    return this;
  }
  private Double visibility;
  public Double get_visibility() {
    return visibility;
  }
  public void set_visibility(Double visibility) {
    this.visibility = visibility;
  }
  public weather with_visibility(Double visibility) {
    this.visibility = visibility;
    return this;
  }
  private Integer uv_index;
  public Integer get_uv_index() {
    return uv_index;
  }
  public void set_uv_index(Integer uv_index) {
    this.uv_index = uv_index;
  }
  public weather with_uv_index(Integer uv_index) {
    this.uv_index = uv_index;
    return this;
  }
  private Double severe_risk;
  public Double get_severe_risk() {
    return severe_risk;
  }
  public void set_severe_risk(Double severe_risk) {
    this.severe_risk = severe_risk;
  }
  public weather with_severe_risk(Double severe_risk) {
    this.severe_risk = severe_risk;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof weather)) {
      return false;
    }
    weather that = (weather) o;
    boolean equal = true;
    equal = equal && (this.station_name == null ? that.station_name == null : this.station_name.equals(that.station_name));
    equal = equal && (this.station_address == null ? that.station_address == null : this.station_address.equals(that.station_address));
    equal = equal && (this.resolved_address == null ? that.resolved_address == null : this.resolved_address.equals(that.resolved_address));
    equal = equal && (this.date_id == null ? that.date_id == null : this.date_id.equals(that.date_id));
    equal = equal && (this.temperature == null ? that.temperature == null : this.temperature.equals(that.temperature));
    equal = equal && (this.feels_like == null ? that.feels_like == null : this.feels_like.equals(that.feels_like));
    equal = equal && (this.dew_point == null ? that.dew_point == null : this.dew_point.equals(that.dew_point));
    equal = equal && (this.humidity == null ? that.humidity == null : this.humidity.equals(that.humidity));
    equal = equal && (this.precipitation == null ? that.precipitation == null : this.precipitation.equals(that.precipitation));
    equal = equal && (this.precipitation_prob == null ? that.precipitation_prob == null : this.precipitation_prob.equals(that.precipitation_prob));
    equal = equal && (this.precipitation_type == null ? that.precipitation_type == null : this.precipitation_type.equals(that.precipitation_type));
    equal = equal && (this.snow == null ? that.snow == null : this.snow.equals(that.snow));
    equal = equal && (this.snow_depth == null ? that.snow_depth == null : this.snow_depth.equals(that.snow_depth));
    equal = equal && (this.wind_gust == null ? that.wind_gust == null : this.wind_gust.equals(that.wind_gust));
    equal = equal && (this.wind_speed == null ? that.wind_speed == null : this.wind_speed.equals(that.wind_speed));
    equal = equal && (this.wind_direction == null ? that.wind_direction == null : this.wind_direction.equals(that.wind_direction));
    equal = equal && (this.sea_level_pressure == null ? that.sea_level_pressure == null : this.sea_level_pressure.equals(that.sea_level_pressure));
    equal = equal && (this.cloud_cover == null ? that.cloud_cover == null : this.cloud_cover.equals(that.cloud_cover));
    equal = equal && (this.visibility == null ? that.visibility == null : this.visibility.equals(that.visibility));
    equal = equal && (this.uv_index == null ? that.uv_index == null : this.uv_index.equals(that.uv_index));
    equal = equal && (this.severe_risk == null ? that.severe_risk == null : this.severe_risk.equals(that.severe_risk));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof weather)) {
      return false;
    }
    weather that = (weather) o;
    boolean equal = true;
    equal = equal && (this.station_name == null ? that.station_name == null : this.station_name.equals(that.station_name));
    equal = equal && (this.station_address == null ? that.station_address == null : this.station_address.equals(that.station_address));
    equal = equal && (this.resolved_address == null ? that.resolved_address == null : this.resolved_address.equals(that.resolved_address));
    equal = equal && (this.date_id == null ? that.date_id == null : this.date_id.equals(that.date_id));
    equal = equal && (this.temperature == null ? that.temperature == null : this.temperature.equals(that.temperature));
    equal = equal && (this.feels_like == null ? that.feels_like == null : this.feels_like.equals(that.feels_like));
    equal = equal && (this.dew_point == null ? that.dew_point == null : this.dew_point.equals(that.dew_point));
    equal = equal && (this.humidity == null ? that.humidity == null : this.humidity.equals(that.humidity));
    equal = equal && (this.precipitation == null ? that.precipitation == null : this.precipitation.equals(that.precipitation));
    equal = equal && (this.precipitation_prob == null ? that.precipitation_prob == null : this.precipitation_prob.equals(that.precipitation_prob));
    equal = equal && (this.precipitation_type == null ? that.precipitation_type == null : this.precipitation_type.equals(that.precipitation_type));
    equal = equal && (this.snow == null ? that.snow == null : this.snow.equals(that.snow));
    equal = equal && (this.snow_depth == null ? that.snow_depth == null : this.snow_depth.equals(that.snow_depth));
    equal = equal && (this.wind_gust == null ? that.wind_gust == null : this.wind_gust.equals(that.wind_gust));
    equal = equal && (this.wind_speed == null ? that.wind_speed == null : this.wind_speed.equals(that.wind_speed));
    equal = equal && (this.wind_direction == null ? that.wind_direction == null : this.wind_direction.equals(that.wind_direction));
    equal = equal && (this.sea_level_pressure == null ? that.sea_level_pressure == null : this.sea_level_pressure.equals(that.sea_level_pressure));
    equal = equal && (this.cloud_cover == null ? that.cloud_cover == null : this.cloud_cover.equals(that.cloud_cover));
    equal = equal && (this.visibility == null ? that.visibility == null : this.visibility.equals(that.visibility));
    equal = equal && (this.uv_index == null ? that.uv_index == null : this.uv_index.equals(that.uv_index));
    equal = equal && (this.severe_risk == null ? that.severe_risk == null : this.severe_risk.equals(that.severe_risk));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.station_name = JdbcWritableBridge.readString(1, __dbResults);
    this.station_address = JdbcWritableBridge.readString(2, __dbResults);
    this.resolved_address = JdbcWritableBridge.readString(3, __dbResults);
    this.date_id = JdbcWritableBridge.readDate(4, __dbResults);
    this.temperature = JdbcWritableBridge.readDouble(5, __dbResults);
    this.feels_like = JdbcWritableBridge.readDouble(6, __dbResults);
    this.dew_point = JdbcWritableBridge.readDouble(7, __dbResults);
    this.humidity = JdbcWritableBridge.readDouble(8, __dbResults);
    this.precipitation = JdbcWritableBridge.readDouble(9, __dbResults);
    this.precipitation_prob = JdbcWritableBridge.readInteger(10, __dbResults);
    this.precipitation_type = JdbcWritableBridge.readString(11, __dbResults);
    this.snow = JdbcWritableBridge.readDouble(12, __dbResults);
    this.snow_depth = JdbcWritableBridge.readDouble(13, __dbResults);
    this.wind_gust = JdbcWritableBridge.readDouble(14, __dbResults);
    this.wind_speed = JdbcWritableBridge.readDouble(15, __dbResults);
    this.wind_direction = JdbcWritableBridge.readDouble(16, __dbResults);
    this.sea_level_pressure = JdbcWritableBridge.readDouble(17, __dbResults);
    this.cloud_cover = JdbcWritableBridge.readDouble(18, __dbResults);
    this.visibility = JdbcWritableBridge.readDouble(19, __dbResults);
    this.uv_index = JdbcWritableBridge.readInteger(20, __dbResults);
    this.severe_risk = JdbcWritableBridge.readDouble(21, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.station_name = JdbcWritableBridge.readString(1, __dbResults);
    this.station_address = JdbcWritableBridge.readString(2, __dbResults);
    this.resolved_address = JdbcWritableBridge.readString(3, __dbResults);
    this.date_id = JdbcWritableBridge.readDate(4, __dbResults);
    this.temperature = JdbcWritableBridge.readDouble(5, __dbResults);
    this.feels_like = JdbcWritableBridge.readDouble(6, __dbResults);
    this.dew_point = JdbcWritableBridge.readDouble(7, __dbResults);
    this.humidity = JdbcWritableBridge.readDouble(8, __dbResults);
    this.precipitation = JdbcWritableBridge.readDouble(9, __dbResults);
    this.precipitation_prob = JdbcWritableBridge.readInteger(10, __dbResults);
    this.precipitation_type = JdbcWritableBridge.readString(11, __dbResults);
    this.snow = JdbcWritableBridge.readDouble(12, __dbResults);
    this.snow_depth = JdbcWritableBridge.readDouble(13, __dbResults);
    this.wind_gust = JdbcWritableBridge.readDouble(14, __dbResults);
    this.wind_speed = JdbcWritableBridge.readDouble(15, __dbResults);
    this.wind_direction = JdbcWritableBridge.readDouble(16, __dbResults);
    this.sea_level_pressure = JdbcWritableBridge.readDouble(17, __dbResults);
    this.cloud_cover = JdbcWritableBridge.readDouble(18, __dbResults);
    this.visibility = JdbcWritableBridge.readDouble(19, __dbResults);
    this.uv_index = JdbcWritableBridge.readInteger(20, __dbResults);
    this.severe_risk = JdbcWritableBridge.readDouble(21, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void loadLargeObjects0(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(station_name, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(station_address, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(resolved_address, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeDate(date_id, 4 + __off, 91, __dbStmt);
    JdbcWritableBridge.writeDouble(temperature, 5 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(feels_like, 6 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(dew_point, 7 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(humidity, 8 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(precipitation, 9 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeInteger(precipitation_prob, 10 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(precipitation_type, 11 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeDouble(snow, 12 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(snow_depth, 13 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(wind_gust, 14 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(wind_speed, 15 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(wind_direction, 16 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(sea_level_pressure, 17 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(cloud_cover, 18 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(visibility, 19 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeInteger(uv_index, 20 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeDouble(severe_risk, 21 + __off, 8, __dbStmt);
    return 21;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(station_name, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(station_address, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(resolved_address, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeDate(date_id, 4 + __off, 91, __dbStmt);
    JdbcWritableBridge.writeDouble(temperature, 5 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(feels_like, 6 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(dew_point, 7 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(humidity, 8 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(precipitation, 9 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeInteger(precipitation_prob, 10 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(precipitation_type, 11 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeDouble(snow, 12 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(snow_depth, 13 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(wind_gust, 14 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(wind_speed, 15 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(wind_direction, 16 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(sea_level_pressure, 17 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(cloud_cover, 18 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(visibility, 19 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeInteger(uv_index, 20 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeDouble(severe_risk, 21 + __off, 8, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.station_name = null;
    } else {
    this.station_name = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.station_address = null;
    } else {
    this.station_address = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.resolved_address = null;
    } else {
    this.resolved_address = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.date_id = null;
    } else {
    this.date_id = new Date(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.temperature = null;
    } else {
    this.temperature = Double.valueOf(__dataIn.readDouble());
    }
    if (__dataIn.readBoolean()) { 
        this.feels_like = null;
    } else {
    this.feels_like = Double.valueOf(__dataIn.readDouble());
    }
    if (__dataIn.readBoolean()) { 
        this.dew_point = null;
    } else {
    this.dew_point = Double.valueOf(__dataIn.readDouble());
    }
    if (__dataIn.readBoolean()) { 
        this.humidity = null;
    } else {
    this.humidity = Double.valueOf(__dataIn.readDouble());
    }
    if (__dataIn.readBoolean()) { 
        this.precipitation = null;
    } else {
    this.precipitation = Double.valueOf(__dataIn.readDouble());
    }
    if (__dataIn.readBoolean()) { 
        this.precipitation_prob = null;
    } else {
    this.precipitation_prob = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.precipitation_type = null;
    } else {
    this.precipitation_type = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.snow = null;
    } else {
    this.snow = Double.valueOf(__dataIn.readDouble());
    }
    if (__dataIn.readBoolean()) { 
        this.snow_depth = null;
    } else {
    this.snow_depth = Double.valueOf(__dataIn.readDouble());
    }
    if (__dataIn.readBoolean()) { 
        this.wind_gust = null;
    } else {
    this.wind_gust = Double.valueOf(__dataIn.readDouble());
    }
    if (__dataIn.readBoolean()) { 
        this.wind_speed = null;
    } else {
    this.wind_speed = Double.valueOf(__dataIn.readDouble());
    }
    if (__dataIn.readBoolean()) { 
        this.wind_direction = null;
    } else {
    this.wind_direction = Double.valueOf(__dataIn.readDouble());
    }
    if (__dataIn.readBoolean()) { 
        this.sea_level_pressure = null;
    } else {
    this.sea_level_pressure = Double.valueOf(__dataIn.readDouble());
    }
    if (__dataIn.readBoolean()) { 
        this.cloud_cover = null;
    } else {
    this.cloud_cover = Double.valueOf(__dataIn.readDouble());
    }
    if (__dataIn.readBoolean()) { 
        this.visibility = null;
    } else {
    this.visibility = Double.valueOf(__dataIn.readDouble());
    }
    if (__dataIn.readBoolean()) { 
        this.uv_index = null;
    } else {
    this.uv_index = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.severe_risk = null;
    } else {
    this.severe_risk = Double.valueOf(__dataIn.readDouble());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.station_name) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, station_name);
    }
    if (null == this.station_address) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, station_address);
    }
    if (null == this.resolved_address) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, resolved_address);
    }
    if (null == this.date_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.date_id.getTime());
    }
    if (null == this.temperature) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.temperature);
    }
    if (null == this.feels_like) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.feels_like);
    }
    if (null == this.dew_point) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.dew_point);
    }
    if (null == this.humidity) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.humidity);
    }
    if (null == this.precipitation) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.precipitation);
    }
    if (null == this.precipitation_prob) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.precipitation_prob);
    }
    if (null == this.precipitation_type) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, precipitation_type);
    }
    if (null == this.snow) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.snow);
    }
    if (null == this.snow_depth) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.snow_depth);
    }
    if (null == this.wind_gust) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.wind_gust);
    }
    if (null == this.wind_speed) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.wind_speed);
    }
    if (null == this.wind_direction) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.wind_direction);
    }
    if (null == this.sea_level_pressure) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.sea_level_pressure);
    }
    if (null == this.cloud_cover) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.cloud_cover);
    }
    if (null == this.visibility) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.visibility);
    }
    if (null == this.uv_index) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.uv_index);
    }
    if (null == this.severe_risk) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.severe_risk);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.station_name) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, station_name);
    }
    if (null == this.station_address) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, station_address);
    }
    if (null == this.resolved_address) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, resolved_address);
    }
    if (null == this.date_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.date_id.getTime());
    }
    if (null == this.temperature) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.temperature);
    }
    if (null == this.feels_like) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.feels_like);
    }
    if (null == this.dew_point) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.dew_point);
    }
    if (null == this.humidity) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.humidity);
    }
    if (null == this.precipitation) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.precipitation);
    }
    if (null == this.precipitation_prob) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.precipitation_prob);
    }
    if (null == this.precipitation_type) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, precipitation_type);
    }
    if (null == this.snow) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.snow);
    }
    if (null == this.snow_depth) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.snow_depth);
    }
    if (null == this.wind_gust) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.wind_gust);
    }
    if (null == this.wind_speed) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.wind_speed);
    }
    if (null == this.wind_direction) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.wind_direction);
    }
    if (null == this.sea_level_pressure) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.sea_level_pressure);
    }
    if (null == this.cloud_cover) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.cloud_cover);
    }
    if (null == this.visibility) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.visibility);
    }
    if (null == this.uv_index) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.uv_index);
    }
    if (null == this.severe_risk) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.severe_risk);
    }
  }
  private static final DelimiterSet __outputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(station_name==null?"null":station_name, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(station_address==null?"null":station_address, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(resolved_address==null?"null":resolved_address, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(date_id==null?"null":"" + date_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(temperature==null?"null":"" + temperature, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(feels_like==null?"null":"" + feels_like, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(dew_point==null?"null":"" + dew_point, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(humidity==null?"null":"" + humidity, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(precipitation==null?"null":"" + precipitation, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(precipitation_prob==null?"null":"" + precipitation_prob, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(precipitation_type==null?"null":precipitation_type, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(snow==null?"null":"" + snow, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(snow_depth==null?"null":"" + snow_depth, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(wind_gust==null?"null":"" + wind_gust, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(wind_speed==null?"null":"" + wind_speed, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(wind_direction==null?"null":"" + wind_direction, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(sea_level_pressure==null?"null":"" + sea_level_pressure, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(cloud_cover==null?"null":"" + cloud_cover, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(visibility==null?"null":"" + visibility, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(uv_index==null?"null":"" + uv_index, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(severe_risk==null?"null":"" + severe_risk, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(station_name==null?"null":station_name, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(station_address==null?"null":station_address, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(resolved_address==null?"null":resolved_address, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(date_id==null?"null":"" + date_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(temperature==null?"null":"" + temperature, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(feels_like==null?"null":"" + feels_like, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(dew_point==null?"null":"" + dew_point, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(humidity==null?"null":"" + humidity, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(precipitation==null?"null":"" + precipitation, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(precipitation_prob==null?"null":"" + precipitation_prob, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(precipitation_type==null?"null":precipitation_type, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(snow==null?"null":"" + snow, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(snow_depth==null?"null":"" + snow_depth, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(wind_gust==null?"null":"" + wind_gust, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(wind_speed==null?"null":"" + wind_speed, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(wind_direction==null?"null":"" + wind_direction, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(sea_level_pressure==null?"null":"" + sea_level_pressure, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(cloud_cover==null?"null":"" + cloud_cover, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(visibility==null?"null":"" + visibility, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(uv_index==null?"null":"" + uv_index, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(severe_risk==null?"null":"" + severe_risk, delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.station_name = null; } else {
      this.station_name = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.station_address = null; } else {
      this.station_address = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.resolved_address = null; } else {
      this.resolved_address = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.date_id = null; } else {
      this.date_id = java.sql.Date.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.temperature = null; } else {
      this.temperature = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.feels_like = null; } else {
      this.feels_like = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.dew_point = null; } else {
      this.dew_point = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.humidity = null; } else {
      this.humidity = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.precipitation = null; } else {
      this.precipitation = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.precipitation_prob = null; } else {
      this.precipitation_prob = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.precipitation_type = null; } else {
      this.precipitation_type = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.snow = null; } else {
      this.snow = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.snow_depth = null; } else {
      this.snow_depth = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.wind_gust = null; } else {
      this.wind_gust = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.wind_speed = null; } else {
      this.wind_speed = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.wind_direction = null; } else {
      this.wind_direction = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.sea_level_pressure = null; } else {
      this.sea_level_pressure = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.cloud_cover = null; } else {
      this.cloud_cover = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.visibility = null; } else {
      this.visibility = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.uv_index = null; } else {
      this.uv_index = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.severe_risk = null; } else {
      this.severe_risk = Double.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.station_name = null; } else {
      this.station_name = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.station_address = null; } else {
      this.station_address = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.resolved_address = null; } else {
      this.resolved_address = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.date_id = null; } else {
      this.date_id = java.sql.Date.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.temperature = null; } else {
      this.temperature = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.feels_like = null; } else {
      this.feels_like = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.dew_point = null; } else {
      this.dew_point = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.humidity = null; } else {
      this.humidity = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.precipitation = null; } else {
      this.precipitation = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.precipitation_prob = null; } else {
      this.precipitation_prob = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.precipitation_type = null; } else {
      this.precipitation_type = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.snow = null; } else {
      this.snow = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.snow_depth = null; } else {
      this.snow_depth = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.wind_gust = null; } else {
      this.wind_gust = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.wind_speed = null; } else {
      this.wind_speed = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.wind_direction = null; } else {
      this.wind_direction = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.sea_level_pressure = null; } else {
      this.sea_level_pressure = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.cloud_cover = null; } else {
      this.cloud_cover = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.visibility = null; } else {
      this.visibility = Double.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.uv_index = null; } else {
      this.uv_index = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.severe_risk = null; } else {
      this.severe_risk = Double.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    weather o = (weather) super.clone();
    o.date_id = (o.date_id != null) ? (java.sql.Date) o.date_id.clone() : null;
    return o;
  }

  public void clone0(weather o) throws CloneNotSupportedException {
    o.date_id = (o.date_id != null) ? (java.sql.Date) o.date_id.clone() : null;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("station_name", this.station_name);
    __sqoop$field_map.put("station_address", this.station_address);
    __sqoop$field_map.put("resolved_address", this.resolved_address);
    __sqoop$field_map.put("date_id", this.date_id);
    __sqoop$field_map.put("temperature", this.temperature);
    __sqoop$field_map.put("feels_like", this.feels_like);
    __sqoop$field_map.put("dew_point", this.dew_point);
    __sqoop$field_map.put("humidity", this.humidity);
    __sqoop$field_map.put("precipitation", this.precipitation);
    __sqoop$field_map.put("precipitation_prob", this.precipitation_prob);
    __sqoop$field_map.put("precipitation_type", this.precipitation_type);
    __sqoop$field_map.put("snow", this.snow);
    __sqoop$field_map.put("snow_depth", this.snow_depth);
    __sqoop$field_map.put("wind_gust", this.wind_gust);
    __sqoop$field_map.put("wind_speed", this.wind_speed);
    __sqoop$field_map.put("wind_direction", this.wind_direction);
    __sqoop$field_map.put("sea_level_pressure", this.sea_level_pressure);
    __sqoop$field_map.put("cloud_cover", this.cloud_cover);
    __sqoop$field_map.put("visibility", this.visibility);
    __sqoop$field_map.put("uv_index", this.uv_index);
    __sqoop$field_map.put("severe_risk", this.severe_risk);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("station_name", this.station_name);
    __sqoop$field_map.put("station_address", this.station_address);
    __sqoop$field_map.put("resolved_address", this.resolved_address);
    __sqoop$field_map.put("date_id", this.date_id);
    __sqoop$field_map.put("temperature", this.temperature);
    __sqoop$field_map.put("feels_like", this.feels_like);
    __sqoop$field_map.put("dew_point", this.dew_point);
    __sqoop$field_map.put("humidity", this.humidity);
    __sqoop$field_map.put("precipitation", this.precipitation);
    __sqoop$field_map.put("precipitation_prob", this.precipitation_prob);
    __sqoop$field_map.put("precipitation_type", this.precipitation_type);
    __sqoop$field_map.put("snow", this.snow);
    __sqoop$field_map.put("snow_depth", this.snow_depth);
    __sqoop$field_map.put("wind_gust", this.wind_gust);
    __sqoop$field_map.put("wind_speed", this.wind_speed);
    __sqoop$field_map.put("wind_direction", this.wind_direction);
    __sqoop$field_map.put("sea_level_pressure", this.sea_level_pressure);
    __sqoop$field_map.put("cloud_cover", this.cloud_cover);
    __sqoop$field_map.put("visibility", this.visibility);
    __sqoop$field_map.put("uv_index", this.uv_index);
    __sqoop$field_map.put("severe_risk", this.severe_risk);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
