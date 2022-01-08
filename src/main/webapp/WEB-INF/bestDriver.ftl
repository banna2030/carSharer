<!DOCTYPE html>
<html lang="de">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
      crossorigin="anonymous"
    />
    <style>
      body {
        background: #eee;
      }
      .container {
        max-width: 800px;
      }
    </style>
    <title>Best Driver</title>
  </head>
  <body>
    <div class="container py-4">
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

      <div class="card rounded shadow-sm">
        <div class="card-body">
          <h2 class="text-center mb-3">offene Fahrten des "besten Fahrers"</h2>

          <!-- driver data area -->
          <div class="card mb-3 rounded shadow-sm">
            <div class="card-body">
              <div class="d-flex justify-content-between align-items-center">
                <label for="rider name"><b>Fahrer:</b></label>
                <label for="average rating" class=""><b>Durchschnittsrating:</b></label>
              </div>
            </div>
          </div>

          <!-- Drives Table -->
          <div class="card mb-3 rounded shadow-sm">
            <div class="card-body">
              <table class="table">
                <tr>
                  <th>Icon</th>
                  <th>Fahrt-ID</th>
                  <th>Von</th>
                  <th>Nach</th>
                </tr>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>

    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
