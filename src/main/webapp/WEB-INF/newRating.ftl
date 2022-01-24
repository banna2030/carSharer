<#include "header.ftl">
<div class="card rounded shadow-sm">
    <div class="card-body">
        <h2 class="text mb-3">Fahrt Bewertung</h2>
        <!-- Form is used for items that will take input from the user -->
        <form action="new_rating" method="get" id="form">
            <!-- text review area -->
            <div class="form-group row mb-5">
                <label for="review" class="col-sm-3 col-form-label">Bewertung:</label>

                <textarea class="col-sm-9" id="review" name="review" rows="7"
                          placeholder="Leave your comment here"></textarea>

            </div>
            <!-- nummerical rating area -->
            <div class="form-group row mb-5">
                <label class="col-sm-3 col-form-label">rating:</label>
                <!-- choices -->
                <div class="col-sm-9">
                    <!-- 1 -->
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="rating" id="rd1" value="1"/>
                        <label class="form-check-label" for="rd1"> 1 </label>
                    </div>
                    <!-- 2 -->
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="rating" id="rd2" value="2"/>
                        <label class="form-check-label" for="rd2"> 2 </label>
                    </div>
                    <!-- 3 -->
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="rating" id="rd3" value="3"/>
                        <label class="form-check-label" for="rd3"> 3 </label>
                    </div>
                    <!-- 4 -->
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="rating" id="rd4" value="4"/>
                        <label class="form-check-label" for="rd4"> 4 </label>
                    </div>
                    <!-- 5 -->
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="rating" id="rd5" value="5"/>
                        <label class="form-check-label" for="rd5"> 5 </label>
                    </div>
                </div>
            </div>
            <!-- submit button-->
            <input type="submit"
                   class="btn btn-primary d-block mb-2 float-end alert"
                   value="Bewerten"
                   onclick="submitButton()"
            />

        </form>
    </div>
</div>
<
<script type="text/javascript">
    function submitButton() {
        var notValid =
            !document.getElementById("rd1").checked
            && !document.getElementById("rd2").checked
            && !document.getElementById("rd3").checked
            && !document.getElementById("rd4").checked
            && !document.getElementById("rd5").checked
            || document.getElementById("review").value == "";
        if (notValid)
            alert("Bitte Bewertung und Rating Eingeben!");
        else {
            document.getElementById("form").method = "post";
        }
    }
</script>
<#include "footer.ftl">