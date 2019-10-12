package com.shu.wms.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="submit_order")
public class SubmitOrderModel extends BaseModel {

    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="submit_order_uuid",strategy="uuid")
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
     * 下委托日期
     */
    @Column(name = "submit_date")
    private Date submitDate;
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

    @OneToMany(mappedBy="submitOrderUuid")
    @LazyCollection(LazyCollectionOption.EXTRA)
    private List<SubmitOrderDetailModel> detailList;

    public List<SubmitOrderDetailModel> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<SubmitOrderDetailModel> detailList) {
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

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
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
