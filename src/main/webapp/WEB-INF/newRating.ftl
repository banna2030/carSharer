<!DOCTYPE html>
<html lang="de">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Fahrt bewerten</title>
    <style>
      body {
        background: #eee;
      }
      .container {
        max-width: 800px;
      }
      }
    </style>
    <<script type="text/javascript">
      function fn1(){
        var notValid=     !document.getElementById("rd1").checked
                && !document.getElementById("rd2").checked
                && !document.getElementById("rd3").checked
                && !document.getElementById("rd4").checked
                && !document.getElementById("rd5").checked
                || document.getElementById("review").value=="";
        if(notValid)
          alert("Bitte Bewertung und Rating Eingeben!");
        else {
          alert("Danke für Ihre Bewertung!");
          window.location.href = 'view_drive';
        }
      }
    </script>
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
          <h2 class="text mb-3">Fahrt Bewertung</h2>
          <!-- Form is used for items that will take input from the user -->
          <form>
            <!-- text review area -->
            <div class="form-group row mb-5">
              <label for="review" class="col-sm-3 col-form-label" >Bewertung:</label>

              <textarea class="col-sm-9" id="review" name="review" rows="7" placeholder="Leave your comment here"></textarea>

            </div>
            <!-- nummerical rating area -->
            <div class="form-group row mb-5">
              <label class="col-sm-3 col-form-label">rating:</label>
              <!-- choices -->
              <div class="col-sm-9">
                <!-- 1 -->
                <div class="form-check form-check-inline">
                  <input class="form-check-input" type="radio" name="rating" id="rd1" value="1" />
                  <label class="form-check-label" for="rd1"> 1 </label>
                </div>
                <!-- 2 -->
                <div class="form-check form-check-inline">
                  <input class="form-check-input" type="radio" name="rating" id="rd2" value="2" />
                  <label class="form-check-label" for="rd2"> 2 </label>
                </div>
                <!-- 3 -->
                <div class="form-check form-check-inline">
                  <input class="form-check-input" type="radio" name="rating" id="rd3" value="3" />
                  <label class="form-check-label" for="rd3"> 3 </label>
                </div>
                <!-- 4 -->
                <div class="form-check form-check-inline">
                  <input class="form-check-input" type="radio" name="rating" id="rd4" value="4" />
                  <label class="form-check-label" for="rd4"> 4 </label>
                </div>
                <!-- 5 -->
                <div class="form-check form-check-inline">
                  <input class="form-check-input" type="radio" name="rating" id="rd5" value="5" />
                  <label class="form-check-label" for="rd5"> 5 </label>
                </div>
              </div>
            </div>
            <!-- submit button-->
            <input type="submit"
                   class="btn btn-primary d-block mb-2 float-end alert"
                   value="Bewerten"
                   onclick="fn1()"
            />

          </form>
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
