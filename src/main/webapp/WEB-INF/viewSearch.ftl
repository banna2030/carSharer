<#include "header.ftl">

<div class="card mb-3 rounded shadow-sm">
    <div class="card-body">
        <h2>Fahrt suchen</h2>
        <form method="post">
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
                    <input type="datetime-local" name="Fahrtdatum" class="form-control mb-2" required/>
                </div>
            </div>
            <button type="submit" class="btn btn-primary d-block mb-2 float-end">Suche</button>
        </form>
    </div>
</div>

<div class="card mb-3 rounded shadow-sm">
    <div class="card-body d-flex">
        <div>
            <h2 style="text-align: left">Suchergebnisse</h2>
        </div>
        <table class="table table-striped table-hover" style="cursor:pointer">
            <thead>
            <tr>
                <th scope="col">Icon</th>
                <th scope="col">Von</th>
                <th scope="col">Nach</th>
                <th scope="col">Preis</th>
            </tr>
            </thead>
             <tbody>
             <#list searchDrive as od>
                 <tr onclick="window.location='view_drive?FID=${od.getFID()}';">
                     <td><img class="rounded-circle" alt="Driver" style="width: 50px; height: 50px" src=${od.getIcon()}; alt="Card image cap"></td>
                     <td>${od.getStartort()}</td>
                     <td>${od.getZielort()}</td>
                     <td>${od.getFahrtkosten()}</td>
                 </tr>
             </#list>
             </tbody>
        </table>
    </div>

</div>

<#include "footer.ftl">