package com.coders.rentkun.entities.vehicles.enums.models;

import lombok.Getter;

@Getter
public enum Lexus {
    CT_200h("CT 200h"), ES_200("ES 200"), ES_250("ES 250"), ES_300("ES 300"), ES_300h("ES 300h"), ES_350("ES 350"),
    GS_200t("GS 200t"), GS_250("GS 250"), GS_300("GS 300"), GS_350("GS 350"), GS_430("GS 430"), GS_450h("GS 450h"),
    GX_460("GX 460"), GX_470("GX 470"), HS_250h("HS 250h"), IS_200("IS 200"), IS_200t("IS 200t"), IS_250("IS 250"),
    IS_300("IS 300"), LS_350("LS 350"), LS_430("LS 430"), LS_460("LS 460"), LX_450d("LX 450d"), LX_470("LX 470"),
    LX_500d("LX 500d"), LX_570("LX 570"), LX_600("LX 600"), NX_200("NX 200"), NX_200t("NX 200t"), NX_250("NX 250"),
    NX_300("NX 300"), NX_300h("NX 300h"), NX_350h("NX 350h"), RX_200t("RX 200t"), RX_300("RX 300"), RX_330("RX 330"),
    RX_350("RX 350"), RX_350h("RX 350h"), RX_400h("RX 400h"), RX_500h("RX 500h"), UX_200("UX 200"), UX_250h("UX 250h");

    private final String model;

    Lexus(String model) {
        this.model = model;
    }
}
