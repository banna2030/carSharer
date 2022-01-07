<html>

<head>
    <title>Hauptseite</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <style>
        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
        body{
            background: #eee;
        }
        .container{
            max-width: 800px;
        }
    </style>

</head>

<body>
<div class="container py-4">

    <div class="mb-3 rounded shadow-sm">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <img src="./Images/car.png" width="30" height="30" class="d-inline-block align-top" alt="">
            <a class="navbar-brand" href="/view_main">CarSharer</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-item nav-link active" href="/view_main">Hauptseite</a>
                    <a class="nav-item nav-link active" href="/new_drive">Fahrt Erstellen</a>
                    <a class="nav-item nav-link active" href="/view_drive">Fahrt anzeigen</a>
                    <a class="nav-item nav-link active" href="/view_search">Fahrt suchen</a>
                </div>
            </div>
        </nav>
    </div>

    <div class="card mb-3 rounded shadow-sm">
        <div class="card-body d-flex justify-content-between align-items-center">
            <div>
                <h2 style="text-align: left">Meine reservierten Fahrten</h2>
            </div>
            <div id="carouselExampleIndicators" class="carousel slide d-flex justify-content-between align-items-center" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner d-flex justify-content-between align-items-center">
                    <div class="carousel-item active d-flex justify-content-between align-items-center">
                        <div class="card" style="width: 18rem;">
                            <img class="rounded-circle d-block mr-3" alt="Driver" style="width: 100px; height: 100px; margin-right: 16px" src="./Images/car.png" alt="Card image cap">
                            <div class="card-body">
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item">Von:</li>
                                    <li class="list-group-item">Nach:</li>
                                    <li class="list-group-item">Status:</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
    </div>

    <div class="card mb-3 rounded shadow-sm">
        <div class="card-body d-flex justify-content-between align-items-center">
            <div>
                <h2 style="text-align: left">Offene Fahrten</h2>
            </div>
            <div id="carouselExampleIndicators" class="carousel slide d-flex justify-content-between align-items-center" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner d-flex justify-content-between align-items-center">
                    <div class="carousel-item active d-flex justify-content-between align-items-center">
                        <div class="card" style="width: 18rem;">
                            <img class="rounded-circle d-block mr-3" alt="Driver" style="width: 100px; height: 100px; margin-right: 16px" src="./Images/car.png" alt="Card image cap">
                            <div class="card-body">
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item">Von:</li>
                                    <li class="list-group-item">Nach:</li>
                                    <li class="list-group-item">Freie Plätze:</li>
                                    <li class="list-group-item">Fahrtkosten:</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
        <button type="button" onclick="location.href='new_drive';" class="btn btn-primary d-block mb-2">Fahrt</button>
    </div>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>

</html>