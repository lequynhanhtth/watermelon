function previewFile(input){
    var file = $("input[type=file]").get(0).files[0];

    if(file){
        var reader = new FileReader();

        reader.onload = function(){
            $("#previewImg").attr("src", reader.result);
        }

        reader.readAsDataURL(file);
    }
}
function previewFile1(input){
    var file = $("input[type=file]").get(1).files[0];

    if(file){
        var reader = new FileReader();

        reader.onload = function(){
            $("#previewImg1").attr("src", reader.result);
        }

        reader.readAsDataURL(file);
    }
}