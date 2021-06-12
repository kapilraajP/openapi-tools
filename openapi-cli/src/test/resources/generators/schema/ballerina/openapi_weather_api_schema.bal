public type '200 record {
    Coord coord?;
    # (more info Weather condition codes)
    Weather[] weather?;
    # Internal parameter
    string base?;
    Main main?;
    # Visibility, meter
    int visibility?;
    Wind wind?;
    Clouds clouds?;
    Rain rain?;
    Snow snow?;
    # Time of data calculation, unix, UTC
    int dt?;
    Sys sys?;
    # City ID
    int id?;
    string name?;
    # Internal parameter
    int cod?;
};

public type Coord record {
    # City geo location, longitude
    decimal lon?;
    # City geo location, latitude
    decimal lat?;
};

public type Weather record {
    # Weather condition id
    int id?;
    # Group of weather parameters (Rain, Snow, Extreme etc.)
    string main?;
    # Weather condition within the group
    string description?;
    # Weather icon id
    string icon?;
};

public type Main record {
    # Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    decimal temp?;
    # Atmospheric pressure (on the sea level, if there is no sea_level or grnd_level data), hPa
    int pressure?;
    # Humidity, %
    int humidity?;
    # Minimum temperature at the moment. This is deviation from current temp that is possible for large cities and megalopolises geographically expanded (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    decimal temp_min?;
    # Maximum temperature at the moment. This is deviation from current temp that is possible for large cities and megalopolises geographically expanded (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    decimal temp_max?;
    # Atmospheric pressure on the sea level, hPa
    decimal sea_level?;
    # Atmospheric pressure on the ground level, hPa
    decimal grnd_level?;
};

public type Wind record {
    # Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.
    decimal speed?;
    # Wind direction, degrees (meteorological)
    int deg?;
};

public type Clouds record {
    # Cloudiness, %
    int 'all?;
};

public type Rain record {
    # Rain volume for the last 3 hours
    int '\3h?;
};

public type Snow record {
    # Snow volume for the last 3 hours
    decimal '\3h?;
};

public type Sys record {
    # Internal parameter
    int 'type?;
    # Internal parameter
    int id?;
    # Internal parameter
    decimal message?;
    # Country code (GB, JP etc.)
    string country?;
    # Sunrise time, unix, UTC
    int sunrise?;
    # Sunset time, unix, UTC
    int sunset?;
};
