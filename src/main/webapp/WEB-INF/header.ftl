<!DOCTYPE html>
<html lang="en">
<head>
    <title>Hauptseite</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <style>
        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }

        body {
            background: #eee;
        }

        .container {
            max-width: 800px;
        }

        .error {
            font-size: 15px;
            color: red;
            font-weight: bold;
            text-align: center;
            margin: 0px auto;
        }

        .success {
            font-size: 15px;
            color: green;
            font-weight: bold;
            text-align: center;
            margin: 0px auto;
        }
    </style>

</head>

<body>
<div class="container py-4">

    <div class="mb-3 rounded shadow-sm">
        <!-- Navbar -->
        <div class="mb-3 rounded shadow-sm">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="mx-4">
                    <img src="./Images/car.png" width="30" height="30" class="d-inline-block align-top" alt=""/>
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
                        <a class="nav-item nav-link active mx-4" href="/new_drive">Fahrt Erstellen</a>
                        <a class="nav-item nav-link active mx-4" href="/view_search">Fahrt suchen</a>
                        <a class="nav-item nav-link active mx-4" href="/bonus">Bester Fahrer</a>
                    </div>
                </div>
            </nav>
        </div>
    </div>