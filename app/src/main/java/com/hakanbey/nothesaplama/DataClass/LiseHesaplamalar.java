package com.hakanbey.nothesaplama.DataClass;

public class LiseHesaplamalar {

    private float BIRINCI_YAZILI;
    private float IKINCI_YAZILI;
    private float UCUNCU_YAZILI;
    private float BIRINCI_SOZLU;
    private float IKINCI_SOZLU;
    private float UCUNCU_SOZLU;
    private float BIRINCI_PERFORMANS;
    private float IKINCI_PERFORMANS;
    private float ORTALAMA;

    public LiseHesaplamalar() {
    }

    public LiseHesaplamalar(float BIRINCI_YAZILI, float IKINCI_YAZILI, float UCUNCU_YAZILI, float ORTALAMA) {
        this.BIRINCI_YAZILI = BIRINCI_YAZILI;
        this.IKINCI_YAZILI = IKINCI_YAZILI;
        this.UCUNCU_YAZILI = UCUNCU_YAZILI;
        this.ORTALAMA = ORTALAMA;
    }

    public LiseHesaplamalar(float BIRINCI_YAZILI, float IKINCI_YAZILI, float UCUNCU_YAZILI, float BIRINCI_SOZLU, float ORTALAMA) {
        this.BIRINCI_YAZILI = BIRINCI_YAZILI;
        this.IKINCI_YAZILI = IKINCI_YAZILI;
        this.UCUNCU_YAZILI = UCUNCU_YAZILI;
        this.BIRINCI_SOZLU = BIRINCI_SOZLU;
        this.ORTALAMA = ORTALAMA;
    }


    public LiseHesaplamalar(float BIRINCI_YAZILI, float IKINCI_YAZILI, float ORTALAMA) {
        this.BIRINCI_YAZILI = BIRINCI_YAZILI;
        this.IKINCI_YAZILI = IKINCI_YAZILI;
        this.ORTALAMA = ORTALAMA;
    }

    public LiseHesaplamalar(float BIRINCI_YAZILI, float IKINCI_YAZILI, float UCUNCU_YAZILI, float BIRINCI_SOZLU, float IKINCI_SOZLU, float UCUNCU_SOZLU, float ORTALAMA) {
        this.BIRINCI_YAZILI = BIRINCI_YAZILI;
        this.IKINCI_YAZILI = IKINCI_YAZILI;
        this.UCUNCU_YAZILI = UCUNCU_YAZILI;
        this.BIRINCI_SOZLU = BIRINCI_SOZLU;
        this.IKINCI_SOZLU = IKINCI_SOZLU;
        this.UCUNCU_SOZLU = UCUNCU_SOZLU;
        this.ORTALAMA = ORTALAMA;
    }

    public LiseHesaplamalar(float BIRINCI_YAZILI, float IKINCI_YAZILI, float UCUNCU_YAZILI, float BIRINCI_SOZLU, float IKINCI_SOZLU, float ORTALAMA) {
        this.BIRINCI_YAZILI = BIRINCI_YAZILI;
        this.IKINCI_YAZILI = IKINCI_YAZILI;
        this.UCUNCU_YAZILI = UCUNCU_YAZILI;
        this.BIRINCI_SOZLU = BIRINCI_SOZLU;
        this.IKINCI_SOZLU = IKINCI_SOZLU;
        this.ORTALAMA = ORTALAMA;
    }

    public LiseHesaplamalar(float BIRINCI_YAZILI, float IKINCI_YAZILI, float UCUNCU_YAZILI, float BIRINCI_SOZLU, float IKINCI_SOZLU, float UCUNCU_SOZLU, float BIRINCI_PERFORMANS, float IKINCI_PERFORMANS, float ORTALAMA) {
        this.BIRINCI_YAZILI = BIRINCI_YAZILI;
        this.IKINCI_YAZILI = IKINCI_YAZILI;
        this.UCUNCU_YAZILI = UCUNCU_YAZILI;
        this.BIRINCI_SOZLU = BIRINCI_SOZLU;
        this.IKINCI_SOZLU = IKINCI_SOZLU;
        this.UCUNCU_SOZLU = UCUNCU_SOZLU;
        this.BIRINCI_PERFORMANS = BIRINCI_PERFORMANS;
        this.IKINCI_PERFORMANS = IKINCI_PERFORMANS;
        this.ORTALAMA = ORTALAMA;
    }

    public float getBIRINCI_YAZILI() {
        return BIRINCI_YAZILI;
    }

    public void setBIRINCI_YAZILI(float BIRINCI_YAZILI) {
        this.BIRINCI_YAZILI = BIRINCI_YAZILI;
    }

    public float getIKINCI_YAZILI() {
        return IKINCI_YAZILI;
    }

    public void setIKINCI_YAZILI(float IKINCI_YAZILI) {
        this.IKINCI_YAZILI = IKINCI_YAZILI;
    }

    public float getUCUNCU_YAZILI() {
        return UCUNCU_YAZILI;
    }

    public void setUCUNCU_YAZILI(float UCUNCU_YAZILI) {
        this.UCUNCU_YAZILI = UCUNCU_YAZILI;
    }

    public float getBIRINCI_SOZLU() {
        return BIRINCI_SOZLU;
    }

    public void setBIRINCI_SOZLU(float BIRINCI_SOZLU) {
        this.BIRINCI_SOZLU = BIRINCI_SOZLU;
    }

    public float getIKINCI_SOZLU() {
        return IKINCI_SOZLU;
    }

    public void setIKINCI_SOZLU(float IKINCI_SOZLU) {
        this.IKINCI_SOZLU = IKINCI_SOZLU;
    }

    public float getUCUNCU_SOZLU() {
        return UCUNCU_SOZLU;
    }

    public void setUCUNCU_SOZLU(float UCUNCU_SOZLU) {
        this.UCUNCU_SOZLU = UCUNCU_SOZLU;
    }

    public float getBIRINCI_PERFORMANS() {
        return BIRINCI_PERFORMANS;
    }

    public void setBIRINCI_PERFORMANS(float BIRINCI_PERFORMANS) {
        this.BIRINCI_PERFORMANS = BIRINCI_PERFORMANS;
    }

    public float getIKINCI_PERFORMANS() {
        return IKINCI_PERFORMANS;
    }

    public void setIKINCI_PERFORMANS(float IKINCI_PERFORMANS) {
        this.IKINCI_PERFORMANS = IKINCI_PERFORMANS;
    }

    public float getORTALAMA() {
        return ORTALAMA;
    }

    public void setORTALAMA(float ORTALAMA) {
        this.ORTALAMA = ORTALAMA;
    }
}
