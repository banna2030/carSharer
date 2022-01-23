<#include "header.ftl">

<div class="card mb-3 rounded shadow-sm">
    <div class="card-body">
        <div class="d-flex">
            <img src=${icon} class="rounded-circle d-block mr-3 alt="Driver"; style="width: 100px; height: 100px; margin-right: 16px">
            <div>
                <h2 class="card-title">Informationen</h2>
                <p class="text-secondary">Alle Informationen zur Reise</p>
            </div>
        </div>
        <ul class="list-group list-group-flush">
            <#list driveInformation as di>
                <li class="list-group-item"><b>Anbieter:</b> ${di.getAnbieter()}</li>
                <li class="list-group-item"><b>Fahrtdatum und Uhrzeit: </b> ${di.getFahrtdatumzeit()}</li>
                <li class="list-group-item"><b>Von: </b> ${di.getStartort()}</li>
                <li class="list-group-item"><b>Nach: </b> ${di.getZielort()}</li>
                <li class="list-group-item"><b>Anzahl freier pl&auml;tze: </b> ${di.getFreiplätze()}</li>
                <li class="list-group-item"><b>Fahrkosten: </b> ${di.getFahrtkosten()}</li>
                <li class="list-group-item"><b>Status: </b> ${di.getStatus()}</li>
                <li class="list-group-item"><b>Beschreibung: </b> ${di.getBeschreibung()}</li>

        </ul>
    </div>
</div>

    <div class="card mb-3 rounded shadow-sm">
        <div class="card-body">
                <h2 style="text-align: left;">Aktionsleiste</h2>
                <div class="d-flex justify-content-between">
                    <p>Anzahl Plätze für Reservierung:</p>
                    <form action="view_drive?FID=${di.getFID()}=book" method="post">
                        <div class="number">
                            <input type="number" name="anPlätze" class="form-control mb-2" value="1" min="1" max="2"/>
                        </div>
                        <div>
                            <button type="submit" class="btn btn-primary d-block mb-2"> Fahrt reservieren </button>
                        </div>
                    </form>
                    <form action="view_drive?FID=${di.getFID()}=delete" method="post">
                        <div>
                            <button type="submit" class="btn btn-outline-danger d-block mb-2">Fahrt löschen</button>
                        </div>
                    </form>
                </div>
        </div>
    </div>


    <div class="card mb-3 rounded shadow-sm">
        <div class="card-body">
            <div class="d-flex justify-content-between align-items-center">
                <h2>Bewertungen</h2>
                <h6>Durchschnittsrating: ${avgRating}</h6>
            </div>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th scope="col">Textnachricht:</th>
                <th scope="col">Erstellungsdatum:</th>
                <th scope="col">Rating:</th>
            </tr>
            </thead>
            <tbody>
            <#list driveRating as dr>
                <tr>
                    <td>${dr.getTextnachricht()}</td>
                    <td>${dr.getErstellungsdatum()}</td>
                    <td>${dr.getRating()}</td>
                </tr>
            </#list>
            </tbody>
        </table>
            <button type="button" onclick="window.location='new_rating?FID=${di.getFID()}';" class="btn btn-primary d-block mb-2" style="float:right">Fahrt bewerten</button>
            </#list>
    </div>
    </div>
</div>

<#include "footer.ftl">