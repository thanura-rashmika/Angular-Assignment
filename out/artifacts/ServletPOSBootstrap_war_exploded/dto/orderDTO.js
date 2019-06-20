
function OrderDTO(oid,customer,item,qty,price) {
    let _oid=oid;
    let _customer=customer;
    let _item=item;
    let _qty=qty;
    let _price=price;

    this.getOid=function () {
        return _oid;
    };
    this.getCustomer=function () {
        return _customer;
    };
    this.getItem=function () {
        return _item;
    };
    this.getOrderQty=function () {
        return _qty;
    };
    this.getOrderPrice=function () {
        return _price;
    };

    this.setOid=function(oid){
        _oid=oid;
    };
    this.setCustomer=function (customer) {
         _customer=customer;
    };
    this.setItem=function (item) {
        _item=item;
    };
    this.setOrderQty=function (qty) {
        _qty=qty;
    };
    this.setOrderPrice=function (price) {
        _price=price;
    };

}