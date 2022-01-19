<#include "header.ftl">

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

<#include "footer.ftl">