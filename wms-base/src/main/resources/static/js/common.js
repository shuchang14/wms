layui.define('jquery', function(exports) {
    var $ = layui.jquery;
    var common={
        formData:function(id){
            var obj = new Object();
            var t = $(id).serializeArray();
            $.each(t, function() {
                obj[this.name] = this.value;
            });
            return obj;
        }
    }
    exports('common', common);
});
