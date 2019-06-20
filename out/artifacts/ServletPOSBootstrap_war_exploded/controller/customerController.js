
let customerEndPoint="/customer";

$(document).ready(function () {
    loadAllCustomers();
    $('#idText').val(0);
});

$('#saveButton').click(function () {
    let id = 0;
    let name = $('#nameText').val();
    let address = $('#addressText').val();
    let tel = $('#telText').val();

    $.ajax({
       url:serverUrl+customerEndPoint,
        method:"POST",
        dataType:"json",
        async:true,
        headers:{'operation':'add','Content-Type':'application/json'},
        data:JSON.stringify({
            "cid":parseInt(id),
            "name":name,
            "address":address,
            "mobile":tel
        }),
        success:function (resp) {
            alert(resp.message);
            loadAllCustomers();
        },
        error:function (resp) {
            // resp=JSON.parse(resp.responseText);
            alert("Failed to add !");
        }

    });
});

$('#deleteButton').click(function () {
    let id = $('#idText').val();

    $.ajax({
        url:serverUrl+customerEndPoint+"?"+$.param({
            "cid":parseInt(id)
        }),
        method:"DELETE",
        dataType:"json",
        async:true,
        headers:{'operation':'delete','Content-Type':'application/json'},
        success:function (resp) {
            alert("Deleted !"+resp.message);
            loadAllCustomers();
        },
        error:function (resp) {
            // resp=JSON.parse(resp.responseText);
            alert("Failed to delete !");
        }

    });
});

$('#updateButton').click(function () {
    let id = $('#idText').val();
    let name = $('#nameText').val();
    let address = $('#addressText').val();
    let tel = $('#telText').val();

    $.ajax({
        url:serverUrl+customerEndPoint,
        method:"PUT",
        dataType:"json",
        async:true,
        headers:{'operation':'update','Content-Type':'application/json'},
        data:JSON.stringify({
            "cid":parseInt(id),
            "name":name,
            "address":address,
            "mobile":tel
        }),
        success:function (resp) {
            alert(resp.message);
            loadAllCustomers();
        },
        error:function (resp) {
            // resp=JSON.parse(resp.responseText);
            alert("Failed to update !");
        }

    });
});

$('#searchCustButton').click(function () {
    let id=$('#searchCustText').val();

    $.ajax({
        url:serverUrl+customerEndPoint,
        method:"GET",
        dataType:"json",
        async:true,
        headers:{'operation':'search','Content-Type':'application/json'},
        data:{
            "cid":parseInt(id)
        },
        success:function (resp) {
            $('#idText').val(resp.cid);
            $('#nameText').val(resp.name);
            $('#addressText').val(resp.address);
            $('#telText').val(resp.mobile);

            $('#idText').fill;

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

$('#viewAllButton').click(function () {
    loadAllCustomers();
});

function loadAllCustomers() {
    $.ajax({
        url:serverUrl+customerEndPoint,
        method:"GET",
        dataType:"json",
        async:true,
        headers:{'operation':'getAll','Content-Type':'application/json'},
        success:function (resp) {
            $('#tbody').empty();
            customerArray=[];
            $('#customerCombo').empty();
            $('#customerCombo').append("<option>(select a customer)</option>");
            for (let i = 0; i < resp.length; i++) {
                customerArray.push(resp[i]);
                $('#custTable #tbody').append(
                    "<tr>" +
                    "<td>"+resp[i].cid+"</td>" +
                    "<td>"+resp[i].name+"</td>" +
                    "<td>"+resp[i].address+"</td>" +
                    "<td>"+resp[i].mobile+"</td>" +
                    "</tr>"
                );
                $('#customerCombo').append("<option>"+resp[i].name+"</option>");
            }
        },
        error:function (resp) {
            // resp=JSON.parse(resp.responseText);
            alert("Failed to getall !");
        }

    });
}
