function CustomerDTO(id,name,address,tel) {
    let _id = id;
    let _name = name;
    let _address = address;
    let _tel = tel;

    this.getId=function () {
        return _id;
    };
    this.getName=function () {
        return _name;
    };
    this.getAddress=function () {
        return _address;
    };
    this.getTel=function () {
        return _tel;
    };
    this.setId=function(id){
        _id=id;
    };
    this.setName=function (name) {
        _name=name;
    };
    this.setAddress=function (address) {
        _address=address;
    };
    this.setTel=function (tel) {
        _tel=tel;
    };
}