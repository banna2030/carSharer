<#include "header.ftl">

<div class="card mb-3 rounded shadow-sm">
    <div class="card-body d-flex">
        <div>
            <h2 style="text-align: left">Meine reservierten Fahrten</h2>
        </div>
        <table class="table table-striped table-hover" style="cursor:pointer">
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
                <tr onclick="window.location='view_drive?FID=${rd.getFID()}';">
                    <td><img class="rounded-circle" alt="Driver" style="width: 50px; height: 50px" src=${rd.getIcon()}; alt="Card image cap"></td>
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
        <table class="table table-striped table-hover" style="cursor:pointer">
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
                <tr onclick="window.location='view_drive?FID=${od.getFID()}';">
                    <td><img class="rounded-circle" alt="Driver" style="width: 50px; height: 50px" src=${od.getIcon()}; alt="Card image cap"></td>
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

<#include "footer.ftl">