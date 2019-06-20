
let itemEndPoint="/item";

$(document).ready(function () {
   loadAllItems();
    $('#itemIdText').val(0);
});

$('#itemSaveButton').click(function () {
    let code=0;
    let name=$('#itemNameText').val();
    let qty=parseFloat($('#qtyText').val());
    let price=parseFloat($('#priceText').val());

    $.ajax({
        url:serverUrl+itemEndPoint,
        method:"POST",
        dataType:"json",
        async:true,
        headers:{'operation':'add','Content-Type':'application/json'},
        data:JSON.stringify({
            "code":code,
            "name":name,
            "price":price,
            "qty":qty
        }),
        success:function (resp) {
            alert(resp.message);
            loadAllItems();
        },
        error:function (resp) {
            // resp=JSON.parse(resp.responseText);
            alert("Failed to add !");
        }

    });
});

$('#itemDeleteButton').click(function () {
    let code=parseFloat($('#itemIdText').val());
    $.ajax({
        url:serverUrl+itemEndPoint+"?"+$.param({
            "code":code
        }),
        method:"DELETE",
        dataType:"json",
        async:true,
        headers:{'operation':'delete','Content-Type':'application/json'},
        success:function (resp) {
            alert("Deleted !"+resp.message);
            loadAllItems();
        },
        error:function (resp) {
            // resp=JSON.parse(resp.responseText);
            alert("Failed to delete !");
        }

    });
});

$('#itemUpdateButton').click(function () {
    let code=parseFloat($('#itemIdText').val());
    let name=$('#itemNameText').val();
    let qty=parseFloat($('#qtyText').val());
    let price=parseFloat($('#priceText').val());

    $.ajax({
        url:serverUrl+itemEndPoint,
        method:"PUT",
        dataType:"json",
        async:true,
        headers:{'operation':'update','Content-Type':'application/json'},
        data:JSON.stringify({
            "code":code,
            "name":name,
            "price":price,
            "qty":qty
        }),
        success:function (resp) {
            alert(resp.message);
            loadAllItems();
        },
        error:function (resp) {
            // resp=JSON.parse(resp.responseText);
            alert("Failed to update !");
        }

    });
});

$('#allItemButton').click(function () {
    loadAllItems();
});

$('#itemSearcButton').click(function () {
    let code=parseFloat($('#itemSearchText').val());

    $.ajax({
        url:serverUrl+itemEndPoint,
        method:"GET",
        dataType:"json",
        async:true,
        headers:{'operation':'search','Content-Type':'application/json'},
        data:{
            "code":code
        },
        success:function (resp) {
            $('#itemIdText').val(resp.code);
            $('#itemNameText').val(resp.name);
            $('#qtyText').val(resp.qty);
            $('#priceText').val(resp.price);

            // $('.bmd-label-floating').css("top:","-1rem");
            // $('.bmd-label-floating').css("left:","0");
            // $('.bmd-label-floating').css("font-size:","0.6875rem");
        },
        error:function (resp) {
            // resp=JSON.parse(resp.responseText);
            alert("Failed to search !");
        }

    });
});


function loadAllItems() {
    $.ajax({
        url:serverUrl+itemEndPoint,
        method:"GET",
        dataType:"json",
        async:true,
        headers:{'operation':'getAll','Content-Type':'application/json'},
        success:function (resp) {
            $('#itemTbody').empty();
            itemsArray=[];
            $('#itemCombo').empty();
            $('#itemCombo').append("<option>(select an item)</option>");
            for (let i = 0; i < resp.length; i++) {
                itemsArray.push(resp[i]);
                $('#itemTbody').append(
                    "<tr>" +
                    "<td>"+resp[i].code+"</td>" +
                    "<td>"+resp[i].name+"</td>" +
                    "<td>"+resp[i].qty+"</td>" +
                    "<td>"+resp[i].price+"</td>" +
                    "</tr>"
                );
                $('#itemCombo').append("<option>"+resp[i].name+"</option>");
            }
        },
        error:function (resp) {
            // resp=JSON.parse(resp.responseText);
            alert("Failed to get all !");
        }

    });
}
