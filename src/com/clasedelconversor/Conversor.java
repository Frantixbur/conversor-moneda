package com.clasedelconversor;

import com.google.gson.annotations.SerializedName;

public class Conversor {
    @SerializedName("base_code")
    public String monedaOrigen;
    @SerializedName("target_code")
    public String monedaObjetivo;
    public String monto;
    @SerializedName("conversion_rate")
    public String valorMoneda;
    @SerializedName("conversion_result")
    public String resultadoDeConversion;
}
