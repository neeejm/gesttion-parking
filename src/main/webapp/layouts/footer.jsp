<!--Footer-->
<div class="w-full pt-16 pb-6 text-sm text-center md:text-left fade-in">
    <a class="text-gray-500 no-underline hover:no-underline" href="#">&copy; App 2022</a>
</div>
</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.6.2/dist/chart.min.js"></script>
<script>
    const ctx = document.getElementById('myChart').getContext('2d');
    let sectionData = [];
    fetch(`http://localhost:4000/eparking/api/v1/stats`)
        .then(response => response.json())
        .then(result => {
            console.log()
            let codes = [];
            let nums = [];
            let colors = [];
            for (const k in result) {
                codes.push(k);
                nums.push(result[k]);
                colors.push("#" + Math.floor(Math.random() * 16777215).toString(16));
            }
            const data = {
                labels: codes,
                datasets: [{
                    label: 'Total Expenses',
                    data: nums,
                    backgroundColor: colors
                }]
            };
            const config = {
                type: 'polarArea',
                data: data,
                options: {
                    plugins: {
                        legend: {
                            position: 'bottom',
                        },
                    }
                }
            };

            const chart = new Chart(ctx, config);
        })
        .catch(error => console.log('error', error));
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="script.js"></script>
</body>

</html>