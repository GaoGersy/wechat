package com.gaofen.model;

public class TbBasMetaBlobWithBLOBs extends TbBasMetaBlob {
    private byte[] f_Metadata;

    private byte[] f_Quickimage1;

    private byte[] f_Quickimage2;

    private byte[] f_Quickimage3;

    private byte[] f_Quickimage4;

    private byte[] f_Thumimage;

    private byte[] f_Quickimage5;

    public TbBasMetaBlobWithBLOBs(Short fDataid, String fState, byte[] f_Metadata, byte[] f_Quickimage1, byte[] f_Quickimage2, byte[] f_Quickimage3, byte[] f_Quickimage4, byte[] f_Thumimage, byte[] fQuickimage5) {
        super(fDataid, fState);
        this.f_Metadata = f_Metadata;
        this.f_Quickimage1 = f_Quickimage1;
        this.f_Quickimage2 = f_Quickimage2;
        this.f_Quickimage3 = f_Quickimage3;
        this.f_Quickimage4 = f_Quickimage4;
        this.f_Thumimage = f_Thumimage;
        this.f_Quickimage5 = fQuickimage5;
    }

    public TbBasMetaBlobWithBLOBs() {
        super();
    }

    public byte[] getF_Metadata() {
        return f_Metadata;
    }

    public void setF_Metadata(byte[] f_Metadata) {
        this.f_Metadata = f_Metadata;
    }

    public byte[] getF_Quickimage1() {
        return f_Quickimage1;
    }

    public void setF_Quickimage1(byte[] f_Quickimage1) {
        this.f_Quickimage1 = f_Quickimage1;
    }

    public byte[] getF_Quickimage2() {
        return f_Quickimage2;
    }

    public void setF_Quickimage2(byte[] f_Quickimage2) {
        this.f_Quickimage2 = f_Quickimage2;
    }

    public byte[] getF_Quickimage3() {
        return f_Quickimage3;
    }

    public void setF_Quickimage3(byte[] f_Quickimage3) {
        this.f_Quickimage3 = f_Quickimage3;
    }

    public byte[] getF_Quickimage4() {
        return f_Quickimage4;
    }

    public void setF_Quickimage4(byte[] f_Quickimage4) {
        this.f_Quickimage4 = f_Quickimage4;
    }

    public byte[] getF_Thumimage() {
        return f_Thumimage;
    }

    public void setF_Thumimage(byte[] f_Thumimage) {
        this.f_Thumimage = f_Thumimage;
    }

    public byte[] getF_Quickimage5() {
        return f_Quickimage5;
    }

    public void setF_Quickimage5(byte[] f_Quickimage5) {
        this.f_Quickimage5 = f_Quickimage5;
    }
}