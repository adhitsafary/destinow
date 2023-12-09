package com.safari.sukabumiexplore;

public class DataClass {
    private String dataJudul;
    private String dataDeskripsi;
    private String dataJambuka;
    private String dataLokasi;
    private String datadataHarga;
    private String dataLinkMaps;
    private String dataTentang;
    private String dataGambar;
    private String key;

    public String getDataJudul() {
        return dataJudul;
    }

    public void setDataJudul(String dataJudul) {
        this.dataJudul = dataJudul;
    }

    public String getDataDeskripsi() {
        return dataDeskripsi;
    }

    public void setDataDeskripsi(String dataDeskripsi) {
        this.dataDeskripsi = dataDeskripsi;
    }

    public String getDataJambuka() {
        return dataJambuka;
    }

    public void setDataJambuka(String dataJambuka) {
        this.dataJambuka = dataJambuka;
    }

    public String getDataLokasi() {
        return dataLokasi;
    }

    public void setDataLokasi(String dataLokasi) {
        this.dataLokasi = dataLokasi;
    }

    public String getDatadataHarga() {
        return datadataHarga;
    }

    public void setDatadataHarga(String datadataHarga) {
        this.datadataHarga = datadataHarga;
    }

    public String getDataLinkMaps() {
        return dataLinkMaps;
    }

    public void setDataLinkMaps(String dataLinkMaps) {
        this.dataLinkMaps = dataLinkMaps;
    }

    public String getDataTentang() {
        return dataTentang;
    }

    public void setDataTentang(String dataTentang) {
        this.dataTentang = dataTentang;
    }

    public String getDataGambar() {
        return dataGambar;
    }

    public void setDataGambar(String dataGambar) {
        this.dataGambar = dataGambar;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public DataClass(String dataJudul, String dataDeskripsi, String dataJambuka, String dataLokasi, String datadataHarga, String dataLinkMaps, String dataTentang, String dataGambar) {
        this.dataJudul = dataJudul;
        this.dataDeskripsi = dataDeskripsi;
        this.dataJambuka = dataJambuka;
        this.dataLokasi = dataLokasi;
        this.datadataHarga = datadataHarga;
        this.dataLinkMaps = dataLinkMaps;
        this.dataTentang = dataTentang;
        this.dataGambar = dataGambar;
    }



    /* public String getKey() {
            return key;
        }
        public void setKey(String key) {
            this.key = key;
        }
        public String getDataTitle() {
            return dataTitle;
        }
        public String getDataDesc() {
            return dataDesc;
        }
        public String getDataLang() {
            return dataLang;
        }
        public String getDataImage() {
            return dataImage;
        }
        public DataClass(String dataTitle, String dataDesc, String dataLang, String dataImage) {
            this.dataTitle = dataTitle;
            this.dataDesc = dataDesc;
            this.dataLang = dataLang;
            this.dataImage = dataImage;
        }

        */
    public DataClass(){
    }
}
