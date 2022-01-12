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
        <!-- Navbar -->
        <div class="mb-3 rounded shadow-sm">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div>
                    <img
                            src="./Images/car.png"
                            width="30"
                            height="30"
                            class="d-inline-block align-top"
                            alt=""
                    />
                    <a class="navbar-brand" href="/view_main">CarSharer</a>
                </div>
                <!-- Hamburger Menu -->
                <button
                        class="navbar-toggler"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#navbarNavAltMarkup"
                        aria-controls="navbarNavAltMarkup"
                        aria-expanded="false"
                        aria-label="Toggle navigation"
                >
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                    <div class="navbar-nav">
                        <a class="nav-item nav-link active" href="/new_drive">Fahrt Erstellen</a>
                        <a class="nav-item nav-link active" href="/view_drive">Fahrt anzeigen</a>
                        <a class="nav-item nav-link active" href="/view_search">Fahrt suchen</a>
                        <a class="nav-item nav-link active" href="/bonus">Bester Fahrer</a>
                        <a class="nav-item nav-link active" href="/new_rating">Fahrt bewerten</a>
                    </div>
                </div>
            </nav>
        </div>
    </div>

    <div class="card mb-3 rounded shadow-sm">
        <div class="card-body d-flex">
            <div>
                <h2 style="text-align: left">Meine reservierten Fahrten</h2>
            </div>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th scope="col">Icon</th>
                    <th scope="col">Von</th>
                    <th scope="col">Nach</th>
                    <th scope="col">Status</th>
                </tr>
                </thead>
                <tbody>
                <#list reservedDrive as rd>
                    <tr>
                        <td><img class="rounded-circle" alt="Driver" style="width: 50px; height: 50px" src="./Images/car.png" alt="Card image cap"></td>
                        <td>${rd.getStartort()}</td>
                        <td>${rd.getZielort()}</td>
                        <td>${rd.getStatus()}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>

    <div class="card mb-3 rounded shadow-sm">
        <div class="card-body d-flex">
            <div>
                <h2 style="text-align: left">Offene Fahrten</h2>
            </div>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th scope="col">Icon</th>
                    <th scope="col">Von</th>
                    <th scope="col">Nach</th>
                    <th scope="col">Freie Plätze</th>
                    <th scope="col">Fahrtkosten</th>
                </tr>
                </thead>
                <tbody>
                <#list openDrive as od>
                    <tr>
                        <td><img class="rounded-circle" alt="Driver" style="width: 50px; height: 50px" src="./Images/car.png" alt="Card image cap"></td>
                        <td>${od.getStartort()}</td>
                        <td>${od.getZielort()}</td>
                        <td>${od.getFreiplätze()}</td>
                        <td>${od.getFahrtkosten()}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
        <button type="button" onclick="location.href='new_drive';" class="btn btn-primary d-block mb-2">Fahrt</button>
    </div>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>

</html>