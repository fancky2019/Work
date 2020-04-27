
$("#btnSetProgress").click(function () {
    // 200ms 打印一次当前的上传进度
    let i=1;
    setInterval(function () {
        if(i<=10)
        {
            $(".progress-bar").css("width",i*10+"%");
            // $(".progress-bar").attr("aria-valuenow",i*10)
            $(".progress-bar").html(i*10+"%");
            i++;
        }

      }, 200);

});


/*
按钮出发File,File-->change-->Input显示路径
 */
$("#openTPSFile").click(function () {
    $("#uploadTPSFile").click();

});


$("#uploadTPSFile").change(function () {

    // $("#selectFilePath").val($("#uploadFile").val());

    // $("#selectFilePath").val($("#uploadFile").val());
    // let file = $("#uploadFile");
    let fullName = $("#uploadTPSFile").val();
    let fileNames = fullName.split("\\");
    let fileName = fileNames[fileNames.length - 1]
    $("#selectTPSFilePath").val(fileName);
});


$("#uploadTPS").click(function (e) {


    $(".progress-bar").css("width",0+"%");
    $(".progress-bar").attr("aria-valuenow",0)
    $(".progress-bar").html(0+"%");

    // 50ms 打印一次当前的上传进度
  var interval=  setInterval(function () {
        $.ajax({
            type:"get",
            dataType: 'json',
            url:"/fileupload/uploadStatus",
            success: function(progress){
                $(".progress-bar").css("width",progress+"%");
                $(".progress-bar").attr("aria-valuenow",progress)
                $(".progress-bar").html(progress+"%");
                if(progress==100)
                {
                    clearInterval(interval);
                }
            }
        });
    }, 50);





    let type = "file";          //后台接收时需要的参数名称，自定义即可
    let id = "uploadTPSFile";            //即input的id，用来寻找值
    let formData = new FormData();
    formData.append(type, $("#" + id)[0].files[0]);    //生成一对表单属性
    $.ajax({
        type: "POST",           //因为是传输文件，所以必须是post
        url: '/fileupload/upload',         //对应的后台处理类的地址
        data: formData,
        processData: false,
        contentType: false,
        success: function (data) {
            // alert('上传成功！');
            // alert(data);
            $("#uploadTPSPath").val(data);
        }
    });



});



