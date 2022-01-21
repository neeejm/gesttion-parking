$("#generator").on("click", () => {
    let gen = "cx";
    if ($('#abc').is(':checked')) { gen = "abc" }
    let num = $("#num").val()
    console.log(num, gen)
})