$(document).ready(function() {
    $("input[type='checkbox']").on('change', function() {
        $("input[type='checkbox']").not(this).prop('checked', false);
        $.ajax({
            type: 'get',
            url : '../cinema/rowseat',
            data : "id=" + $(this).val(),
            success : function(responseText) {
                    $('#rowSeat-view').html(responseText);
            }
        });
    });
});
