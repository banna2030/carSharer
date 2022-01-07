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
    <title>Fahrt bewerten</title>
    <style>
      body {
        background: #eee;
      }
      .container {
        max-width: 800px;
      }
    </style>
  </head>
  <body>
    <div class="container py-4">
      <div class="card rounded shadow-sm">
        <div class="card-body">
          <h2 class="text mb-3">Fahrt Bewertung</h2>
          <!-- Form is used for items that will take input from the user -->
          <form>
            <!-- text review area -->
            <div class="form-group row mb-5">
              <label for="review" class="col-sm-3 col-form-label">Bewertung:</label>
              <textarea class="col-sm-9" id="review" rows="7"></textarea>
            </div>
            <!-- nummerical rating area -->
            <div class="form-group row mb-5">
              <label for="review" class="col-sm-3 col-form-label">Rating:</label>
              <!-- choices -->
              <div class="col-sm-9">
                <!-- 1 -->
                <div class="form-check form-check-inline">
                  <input class="form-check-input" type="radio" name="Rating" id="1" value="1" />
                  <label class="form-check-label" for="1"> 1 </label>
                </div>
                <!-- 2 -->
                <div class="form-check form-check-inline">
                  <input class="form-check-input" type="radio" name="Rating" id="2" value="2" />
                  <label class="form-check-label" for="2"> 2 </label>
                </div>
                <!-- 3 -->
                <div class="form-check form-check-inline">
                  <input class="form-check-input" type="radio" name="Rating" id="3" value="3" />
                  <label class="form-check-label" for="3"> 3 </label>
                </div>
                <!-- 4 -->
                <div class="form-check form-check-inline">
                  <input class="form-check-input" type="radio" name="Rating" id="4" value="4" />
                  <label class="form-check-label" for="4"> 4 </label>
                </div>
                <!-- 5 -->
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="radio"
                    name="Rating"
                    id="5"
                    value="5"
                    checked
                  />
                  <label class="form-check-label" for="5"> 5 </label>
                </div>
              </div>
            </div>
            <button type="submit" class="btn btn-primary d-block mb-2 float-end">Bewerten</button>
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
