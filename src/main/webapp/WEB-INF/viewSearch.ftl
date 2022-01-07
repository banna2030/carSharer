<html>

<head>
    <title>Fahrt suchen</title>
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
        <div class="card-body">
            <h2>Fahrt suchen</h2>
            <form>
                <div class="form-group row mb-2">
                    <label for="from" class="col-sm-3 col-form-label">Start:</label>
                    <div class="col-sm-9">
                        <input name="from"type="text" id="from" class="form-control">
                    </div>
                </div>
                <div class="form-group row mb-2">
                    <label for="to" class="col-sm-3 col-form-label">Ziel:</label>
                    <div class="col-sm-9">
                        <input name="to" type="text" id="to" class="form-control">

                    </div>
                </div>
                <div class="form-group row mb-2">
                    <label for="Fahrtdatum" class="col-sm-3 col-form-label">ab:</label>
                    <div class="col-sm-5">
                        <input type="date" id="Fahrtdatum" name="trip-start" value="2022-02-12" min="2022-01-01" max="2022-12-31"class="form-control">
                    </div>
                </div>
                <button type="submit" class="btn btn-primary d-block mb-2 float-end">Suche</button>
            </form>
        </div>
    </div>

    <div class="card mb-3 rounded shadow-sm">
        <div class="card-body d-flex justify-content-between align-items-center">
            <div>
                <h2 style="text-align: left">Suchergebnisse</h2>
            </div>
            <div id="carouselExampleIndicators" class="carousel slide d-flex justify-content-between align-items-center" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner d-flex justify-content-between align-items-center">
                    <div class="carousel-item active d-flex justify-content-between align-items-center">
                        <div class="card p-3 d-flex" style="width: 18rem;">
                            <div style="margin-left: 75px">
                                <img class="rounded-circle d-block justify-content-center" alt="Driver" style="width: 100px; height: 100px; margin-right: 16px" src="./Images/car.png" alt="Card image cap">
                            </div>

                            <div class="card-body">
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item">Von:</li>
                                    <li class="list-group-item">Nach:</li>
                                    <li class="list-group-item">Preis:</li>
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

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>

</html>