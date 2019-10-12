package com.shu.wms.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="logistics_order")
public class LogisticsOrderModel extends BaseModel {

    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="logistics_order_uuid",strategy="uuid")
    private String logisticsOrderUuid;
    /**
     * 委托单号
     */
    @Column(name = "logistics_order_no")
    private String logisticsOrderNo;
    /**
     * 委托单号
     */
    @Column(name = "submit_order_uuid")
    private String submitOrderUuid;
    /**
     * 委托单号
     */
    @Column(name = "submit_order_no")
    private String submitOrderNo;
    /**
     * 客户委托单号
     */
    @Column(name = "order_no")
    private String orderNo;
    /**
     * 办单日期
     */
    @Column(name = "order_date")
    private Date orderDate;
    /**
     * 项目编号
     */
    @Column(name = "project_Code")
    private String projectCode;
    /**
     * 合同号
     */
    @Column(name = "contract_No")
    private String contractNo;
    /**
     * 委托单位代码
     */
    @Column(name = "agent_Consignee_Code")
    private String agentConsigneeCode;
    /**
     * 委托单位名称
     */
    @Column(name = "agent_Consignee_Desc")
    private String agentConsigneeDesc;
    /**
     * 结算单位代码
     */
    @Column(name = "pay_Code")
    private String payCode;
    /**
     * 结算单位名称
     */
    @Column(name = "pay_Desc")
    private String payDesc;
    /**
     * 出运日期
     */
    @Column(name = "delivery_Date")
    private Date deliveryDate;
    /**
     * 结算日期
     */
    @Column(name = "cut_Off_Date")
    private Date cutOffDate;
    /**
     * 经办人
     */
    @Column(name = "functionary")
    private String functionary;
    /**
     * 联系电话
     */
    @Column(name = "tel_No")
    private String telNo;
    /**
     * 流向
     */
    @Column(name = "flow")
    private String flow;
    /**
     * 上架和拣货策略号
     */
    @Column(name = "config_Code")
    private String configCode;

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
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    @OneToMany(mappedBy="logisticsOrderUuid")
    @LazyCollection(LazyCollectionOption.EXTRA)
    private List<LogisticsOrderDetailModel> detailList;

    public List<LogisticsOrderDetailModel> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<LogisticsOrderDetailModel> detailList) {
        this.detailList = detailList;
    }

    public String getSubmitOrderUuid() {
        return submitOrderUuid;
    }

    public void setSubmitOrderUuid(String submitOrderUuid) {
        this.submitOrderUuid = submitOrderUuid;
    }

    public String getSubmitOrderNo() {
        return submitOrderNo;
    }

    public void setSubmitOrderNo(String submitOrderNo) {
        this.submitOrderNo = submitOrderNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getAgentConsigneeCode() {
        return agentConsigneeCode;
    }

    public void setAgentConsigneeCode(String agentConsigneeCode) {
        this.agentConsigneeCode = agentConsigneeCode;
    }

    public String getAgentConsigneeDesc() {
        return agentConsigneeDesc;
    }

    public void setAgentConsigneeDesc(String agentConsigneeDesc) {
        this.agentConsigneeDesc = agentConsigneeDesc;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPayDesc() {
        return payDesc;
    }

    public void setPayDesc(String payDesc) {
        this.payDesc = payDesc;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getCutOffDate() {
        return cutOffDate;
    }

    public void setCutOffDate(Date cutOffDate) {
        this.cutOffDate = cutOffDate;
    }

    public String getFunctionary() {
        return functionary;
    }

    public void setFunctionary(String functionary) {
        this.functionary = functionary;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public String getConfigCode() {
        return configCode;
    }

    public void setConfigCode(String configCode) {
        this.configCode = configCode;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
