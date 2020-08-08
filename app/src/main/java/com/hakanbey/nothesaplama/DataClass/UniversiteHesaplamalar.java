package com.hakanbey.nothesaplama.DataClass;

public class UniversiteHesaplamalar {
    private float VIZE_NOTU;
    private float FINAL_NOTU;
    private float ORTALAMA;

    public UniversiteHesaplamalar() {
    }

    public UniversiteHesaplamalar(float VIZE_NOTU, float FINAL_NOTU, float ORTALAMA) {
        this.VIZE_NOTU = VIZE_NOTU;
        this.FINAL_NOTU = FINAL_NOTU;
        this.ORTALAMA = ORTALAMA;
    }

    public float getVIZE_NOTU() {
        return VIZE_NOTU;
    }

    public void setVIZE_NOTU(float VIZE_NOTU) {
        this.VIZE_NOTU = VIZE_NOTU;
    }

    public float getFINAL_NOTU() {
        return FINAL_NOTU;
    }

    public void setFINAL_NOTU(float FINAL_NOTU) {
        this.FINAL_NOTU = FINAL_NOTU;
    }

    public float getORTALAMA() {
        return ORTALAMA;
    }

    public void setORTALAMA(float ORTALAMA) {
        this.ORTALAMA = ORTALAMA;
    }
}
