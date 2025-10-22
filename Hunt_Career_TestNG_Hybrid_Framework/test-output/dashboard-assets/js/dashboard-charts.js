document.addEventListener('DOMContentLoaded', function () {
    initializeCollapsibles();
    renderCharts();
});

function initializeCollapsibles() {
    const collapsibles = document.getElementsByClassName('collapsible');
    for (let i = 0; i < collapsibles.length; i++) {
        collapsibles[i].addEventListener('click', function () {
            this.classList.toggle('active');
            const content = this.nextElementSibling;
            if (content.style.display === 'block') {
                content.style.display = 'none';
            } else {
                content.style.display = 'block';
            }
        });
    }
}

function renderCharts() {
    if (typeof window.dashboardData === 'undefined') {
        console.error('Dashboard data not found.');
        return;
    }

    renderTrendChart(window.dashboardData.historical);
    renderPerClassCharts(window.dashboardData.currentRun);
}

function renderTrendChart(historicalData) {
    if (!historicalData || historicalData.length === 0) return;

    const labels = historicalData.map(run => run.runId.replace('.json', ''));
    const passPercentages = historicalData.map(run => (run.summary.total > 0 ? (run.summary.passed / run.summary.total) * 100 : 0));
    const failPercentages = historicalData.map(run => (run.summary.total > 0 ? (run.summary.failed / run.summary.total) * 100 : 0));
    const avgDurations = historicalData.map(run => (run.summary.total > 0 ? run.summary.duration / run.summary.total : 0));

    const ctx = document.getElementById('trendChart').getContext('2d');
    new Chart(ctx, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [
                {
                    label: 'Pass %',
                    data: passPercentages,
                    borderColor: '#3fb950',
                    backgroundColor: 'rgba(63, 185, 80, 0.12)',
                    tension: 0.3
                },
                {
                    label: 'Fail %',
                    data: failPercentages,
                    borderColor: '#f85149',
                    backgroundColor: 'rgba(248, 81, 73, 0.12)',
                    tension: 0.3
                },
                {
                    label: 'Avg Duration (ms)',
                    data: avgDurations,
                    borderColor: '#58a6ff',
                    backgroundColor: 'rgba(88, 166, 255, 0.12)',
                    yAxisID: 'yDur',
                    tension: 0.3
                }
            ]
        },
        options: {
            responsive: true,
            interaction: { mode: 'index', intersect: false },
            scales: {
                x: { ticks: { color: '#8b949e' } },
                y: { type: 'linear', position: 'left', ticks: { color: '#8b949e' } },
                yDur: { type: 'linear', position: 'right', ticks: { color: '#8b949e' }, grid: { display: false } }
            },
            plugins: { legend: { labels: { color: '#c9d1d9' } } }
        }
    });
}

function renderPerClassCharts(currentRunData) {
    if (!currentRunData || !currentRunData.classMetrics) return;

    currentRunData.classMetrics.forEach((metrics, index) => {
        const doughnutCtx = document.getElementById(`classChart-${index}`).getContext('2d');
        new Chart(doughnutCtx, {
            type: 'doughnut',
            data: {
                labels: ['Passed', 'Failed', 'Skipped', 'Blocked'],
                datasets: [{
                    data: [metrics.passed, metrics.failed, metrics.skipped, metrics.blocked],
                    backgroundColor: ['#3fb950', '#f85149', '#d29922', '#8b949e'],
                    borderColor: '#161b22',
                    borderWidth: 2
                }]
            },
            options: {
                plugins: { legend: { position: 'bottom', labels: { color: '#c9d1d9' } } },
                cutout: '60%'
            }
        });
    });
}
