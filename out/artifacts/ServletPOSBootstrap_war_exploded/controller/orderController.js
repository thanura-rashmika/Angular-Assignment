
let orderEndPoint="/orders";
let orderCustomer={};
let orderItem={};
let total=0.0;
let orderDetailList=[];
$(document).ready(function () {

});

$('#customerCombo').change(function () {
    orderCustomer=customerArray[$('#customerCombo').prop('selectedIndex')-1];
    $('#itemCombo').focus();
});

$('#itemCombo').change(function () {
    orderItem=itemsArray[$('#itemCombo').prop('selectedIndex')-1];
    $('#orderUnitPrice').val(orderItem.price);
    $('#orderQtyText').focus();
});

$('#orderQtyText').on("keyup",function (e) {

    if (e.keyCode == 13) {
        $('#orderUnitPrice').focus();
    }
});

$('#orderUnitPrice').on("keyup",function (e) {

    if(e.keyCode==13){
        $('#orderTbody')
            .append("<tr>" +
                "<td>"+orderCustomer.name+"</td>" +
                "<td>"+orderItem.name+"</td>" +
                "<td>"+$('#orderQtyText').val()+"</td>"+
                "<td>"+orderItem.price*parseFloat($('#orderQtyText').val())+"</td>"+
                "</tr>"
            );
        total=total+orderItem.price*parseFloat($('#orderQtyText').val());
        $('#totalLabel').text(total);
        orderDetailList.push({
            "code":orderItem.code,
            "oid":0,
            "unitPrice":orderItem.price,
            "qty":parseFloat($('#orderQtyText').val())
        })
    }
});

$('#addOrderButton').click(function () {
    let d = new Date($.now());
    let today=d.getFullYear()+ "-" +(d.getMonth() + 1 )
        +"-" +d.getDate();

    let orderDto={
        "oid":0,
        "date":today,
        "total":total,
        "cid":orderCustomer.cid,
        "orderDetailDTOS":orderDetailList
    };

    $.ajax({
        url:serverUrl+orderEndPoint,
        method:"POST",
        dataType:"json",
        async:true,
        headers:{'operation':'add','Content-Type':'application/json'},
        data:JSON.stringify(orderDto),
        success:function (resp) {
            alert(resp.message);
            orderCustomer={};
            orderItem={};
            total=0.0;
            orderDetailList=[];
            $('#orderTbody').empty();
            $('#totalLabel').val(0.0);
        },
        error:function (resp) {
            // resp=JSON.parse(resp.responseText);
            alert("Failed to add !");
        }

    });

});

$('#orderReloadButton').click(function () {

});