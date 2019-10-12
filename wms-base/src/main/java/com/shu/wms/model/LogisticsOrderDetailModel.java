package com.shu.wms.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="logistics_order_detail")
public class LogisticsOrderDetailModel extends BaseModel {

    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="logistics_order_detail_uuid",strategy="uuid")
    private String logisticsOrderDetailUuid;
    @Column(name = "logistics_order_uuid")
    private String logisticsOrderUuid;
    @Column(name = "logistics_order_no")
    private String logisticsOrderNo;
    @Column(name = "in_Logistics_Order_Detail_Uuid")
    private String inLogisticsOrderDetailUuid;
    /**
     * 运单号
     */
    @Column(name = "bill_No")
    private String billNo;
    /**
     * 批次号
     */
    @Column(name = "batch_No")
    private String batchNo;
    @Column(name = "item_Seqno")
    private String itemSeqno;
    @Column(name = "item_code")
    private String itemCode;
    @Column(name = "goods_desc")
    private String goodsDesc;
    @Column(name = "qty")
    private Double qty;
    @Column(name = "marks_Number")
    private String marksNumber;
    @Column(name = "model")
    private String model;
    @Column(name = "spec")
    private String spec;
    @Column(name = "length")
    private Double length;
    @Column(name = "width")
    private Double width;
    @Column(name = "height")
    private Double height;
    @Column(name = "qty_Unit_Code")
    private String qtyUnitCode;
    @Column(name = "qty_Unit_Desc")
    private String qtyUnitDesc;
    @Column(name = "net_Weight")
    private Double netWeight;
    @Column(name = "gross_Weight")
    private Double grossWeight;
    @Column(name = "volume")
    private Double volume;
    @Column(name = "unit_Price")
    private Double unitPrice;
    @Column(name = "total_Price")
    private Double totalPrice;
    @Column(name = "goods_Kind")
    private String goodsKind;
    /**
     * 状态
     */
    @Column(name = "transaction_Status")
    private String transactionStatus;
    /**
     * 类型
     */
    @Column(name = "transaction_Type")
    private String transactionType;

    public String getLogisticsOrderDetailUuid() {
        return logisticsOrderDetailUuid;
    }

    public void setLogisticsOrderDetailUuid(String logisticsOrderDetailUuid) {
        this.logisticsOrderDetailUuid = logisticsOrderDetailUuid;
    }

    public String getLogisticsOrderUuid() {
        return logisticsOrderUuid;
    }

    public void setLogisticsOrderUuid(String logisticsOrderUuid) {
        this.logisticsOrderUuid = logisticsOrderUuid;
    }

    public String getLogisticsOrderNo() {
        return logisticsOrderNo;
    }

    public void setLogisticsOrderNo(String logisticsOrderNo) {
        this.logisticsOrderNo = logisticsOrderNo;
    }

    public String getInLogisticsOrderDetailUuid() {
        return inLogisticsOrderDetailUuid;
    }

    public void setInLogisticsOrderDetailUuid(String inLogisticsOrderDetailUuid) {
        this.inLogisticsOrderDetailUuid = inLogisticsOrderDetailUuid;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getItemSeqno() {
        return itemSeqno;
    }

    public void setItemSeqno(String itemSeqno) {
        this.itemSeqno = itemSeqno;
    }

    public String getMarksNumber() {
        return marksNumber;
    }

    public void setMarksNumber(String marksNumber) {
        this.marksNumber = marksNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getQtyUnitCode() {
        return qtyUnitCode;
    }

    public void setQtyUnitCode(String qtyUnitCode) {
        this.qtyUnitCode = qtyUnitCode;
    }

    public String getQtyUnitDesc() {
        return qtyUnitDesc;
    }

    public void setQtyUnitDesc(String qtyUnitDesc) {
        this.qtyUnitDesc = qtyUnitDesc;
    }

    public Double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Double netWeight) {
        this.netWeight = netWeight;
    }

    public Double getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(Double grossWeight) {
        this.grossWeight = grossWeight;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getGoodsKind() {
        return goodsKind;
    }

    public void setGoodsKind(String goodsKind) {
        this.goodsKind = goodsKind;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }
}
