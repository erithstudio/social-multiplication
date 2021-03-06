var SERVER_URL = "http://10.240.39.188:8080/api";

function updateLeaderBoard() {
    $.ajax({
        crossdomain: true,
        url: SERVER_URL + "/leaders"
    }).then(function (data) {
        $('#leaderboard-body').empty();
        data.forEach(function (row) {
            $('#leaderboard-body').append('<tr><td>' + row.userId + '</td>' +
                '<td>' + row.totalScore + '</td>');
        });
    });
}

function updateStats(userId) {
    $.ajax({
        url: SERVER_URL + "/stats?userId=" + userId,
        crossdomain: true,
        success: function (data) {
            $('#stats-div').show();
            $('#stats-user-id').empty().append(userId);
            $('#stats-score').empty().append(data.score);
            $('#stats-badges').empty().append(data.badges.join());
        },
        error: function (data) {
            $('#stats-div').show();
            $('#stats-user-id').empty().append(userId);
            $('#stats-score').empty().append(0);
            $('#stats-badges').empty();
        }
    });
}

$(document).ready(function () {
    $.ajaxSetup({
        headers: {
            'Access-Control-Allow-Headers': 'Accept',
            'Sec-Fetch-Site': 'cross-site'
        }
    });

    updateLeaderBoard();

    $("#refresh-leaderboard").click(function (event) {
        updateLeaderBoard();
    });

});