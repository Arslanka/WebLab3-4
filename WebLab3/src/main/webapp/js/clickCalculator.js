function calculateClick() {
    let target = $('#svg-graph');
    let r = $('[id="form:r_value"]').val();
    let x = Math.round(event.clientX - target.position().left);
    let y = event.clientY - target.position().top;
    let xValue = ((x - 170 + $(window).scrollTop()) / (100 / r)).toFixed(3);
    let yValue = ((y - 170 + $(window).scrollTop()) / (-100 / r)).toFixed(3);
    $('[id="form:x_value"]').val(xValue);
    $('[id="form:y_value"]').val(yValue);
    $('[id="form:submit"]').click();
}