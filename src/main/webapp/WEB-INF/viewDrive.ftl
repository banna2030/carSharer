<html>

<head>
    <title>View Drive</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

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

<div class="card mb-5 rounded shadow-sm">
    <div class="card-body">
        <div class="d-flex">
            <img src="./Images/Ahmed.jpg" class="rounded-circle d-block mr-3" alt="Driver" style="width: 100px; height: 100px; margin-right: 16px">
            <div>
                <h2 class="card-title">Informationen</h2>
                <p class="text-secondary">Alle Indormationen über die Fahrer</p>
            </div>
        </div>
        <ul class="list-group list-group-flush">
            <li class="list-group-item">Anbieter</li>
            <li class="list-group-item">Fahrtdatum und Uhrzeit:</li>
            <li class="list-group-item">Von:</li>
            <li class="list-group-item">Nach:</li>
            <li class="list-group-item">Anzahl freier pl&auml;tze:</li>
            <li class="list-group-item">Fahrkosten:</li>
            <li class="list-group-item">Status:</li>
            <li class="list-group-item">Beschreibung:</li>
        </ul>
    </div>
</div>

    <div class="card mb-5 rounded shadow-sm">
        <div class="card-body">
            <h2 style="text-align: left;">Aktionsleiste</h2>
            <div class="d-flex justify-content-between">
                <p>Anzahl Plätze für Reservierung:</p>
                <div class="number">
                    <input type="number" class="form-control" value="1" min="1"/>
                </div>
                <div>
                    <button type="button" onclick="location.href='new_drive';" class="btn btn-primary d-block mb-2"> Fahrt reservieren </button>
                    <button type="button" class="btn btn-outline-danger d-block mb-2">Fahrt löschen</button>
                </div>
            </div>
        </div>
    </div>

    <div class="card mb-5 rounded shadow-sm">
        <div class="card-body">
            <div class="d-flex justify-content-between align-items-center">
                <h2>Bewertungen</h2>
                <h6>Durchschnittsrating:</h6>
            </div>
            <table class="table">
                <tr>
                    <th>Email</th>
                    <th>Beschreibung</th>
                    <th>Bewertung</th>
                </tr>
            </table>
            <button type="button" class="btn btn-primary d-block mb-2">Fahrt bewerten</button>
        </div>
    </div>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>

</html>