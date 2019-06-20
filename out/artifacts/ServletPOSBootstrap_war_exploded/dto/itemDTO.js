function ItemDTO(iid, description, qty, price) {
    let _iid=iid;
    let _description=description;
    let _qty=qty;
    let _price=price;

    this.getIid=function () {
        return _iid;
    };
    this.getDescriptoin=function () {
        return _description;
    };
    this.getQty=function () {
        return _qty;
    };
    this.getPrice=function () {
        return _price;
    };
    this.setIid=function (iid) {
        _iid=iid;
    };
    this.setDescription=function (description) {
        _description=description;
    };
    this.setQty=function (qty) {
        _qty=qty;
    };
    this.setPrice=function (price) {
        _price=price;
    };
}