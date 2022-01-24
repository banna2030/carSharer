<#include "header.ftl">

<div class="card rounded shadow-sm">
    <div class="card-body">
        <h2 class="text-center mb-3">offene Fahrten des "besten Fahrers"</h2>

        <!-- driver data area -->
        <div class="card mb-3 rounded shadow-sm">
            <div class="card-body">
                <div class="container">
                    <div class="row">
                        <div class="col-sm d-flex">
                            <label class="mx-2 mb-0" for="rider_name"><b>Fahrer: </b></label>
                            <p id="rider_name" class="mb-0"> ${driverName}</p>
                        </div>
                        <div class="col-sm d-flex">
                            <label for="average_rating" class="mx-2 mb-0"><b>Durchschnittsrating:</b></label>
                            <p id="average_rating" class="mb-0"> ${averageRating}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Drives Table -->
    <div class="card mb-3 rounded shadow-sm">
        <div class="card-body d-flex">
            <table class="table table-striped table-hover" style="cursor:pointer">
                <thead>
                <tr>
                    <th scope="col">Icon</th>
                    <th scope="col">Fahrt-ID:</th>
                    <th scope="col">Von</th>
                    <th scope="col">Nach</th>
                </tr>
                </thead>
                <tbody>
                <#list bestDriverOpenDrives as bdod>
                    <tr onclick="window.location='view_drive?FID=${bdod.getFID()}';">
                        <td><img class="rounded-circle" alt="Driver" style="width: 50px; height: 50px"
                                 src=${bdod.getIcon()}; alt="Card image cap"></td>
                        <td>${bdod.getFID()}</td>
                        <td>${bdod.getStartort()}</td>
                        <td>${bdod.getZielort()}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>

    <#include "footer.ftl">
