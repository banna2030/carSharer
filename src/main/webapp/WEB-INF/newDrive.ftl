<#include "header.ftl">

<div class="card mb-5 rounded shadow-sm">
    <div class="card-body">
        <h2>Fahrt erstellen</h2>
        <form method="post">
            <div class="form-group row mb-2">
                <label for="from" class="col-sm-3 col-form-label">Von:</label>
                <div class="col-sm-9">
                    <input name="from" type="text" id="from" class="form-control">
                </div>
            </div>
            <div class="form-group row mb-2">
                <label for="to" class="col-sm-3 col-form-label">Bis:</label>
                <div class="col-sm-9">
                    <input name="to" type="text" id="to" class="form-control">
                </div>
            </div>
            <div class="form-group row mb-2">
                <label for="capcity" class="col-sm-3 col-form-label">Maximale Kapzität:</label>
                <div class="col-sm-9">
                    <input name="capacity" type="number" min="1" max="10" id="capcity" class="form-control">
                </div>
            </div>
            <div class="form-group row mb-2">
                <label for="cost" class="col-sm-3 col-form-label">Fahrtkosten (€):</label>
                <div class="col-sm-9">
                    <input name="cost" type="number" min="1" id="cost" class="form-control">
                </div>
            </div>
            <div class="form-group row mb-2">
                <label for="" class="col-sm-3 col-form-label">Transportmittel:</label>
                <div class="col-sm-9">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="Transportmittel" id="Auto" value="1" checked>
                        <label class="form-check-label" for="Auto">
                            Auto
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="Transportmittel" id="Bus" value="2">
                        <label class="form-check-label" for="Bus">
                            Bus
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="Transportmittel" id="Kleintransporter"
                               value="3">
                        <label class="form-check-label" for="Kleintransporter">
                            Kleintransporter
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group row mb-2">
                <label for="Fahrtdatum" class="col-sm-3 col-form-label">Fahrtdatum:</label>
                <div class="col-sm-7">
                    <div class="p-0 col-sm-7 mb-2">
                        <input type="datetime-local" name="Fahrtdatum" class="form-control mb-2" required/>
                    </div>
                </div>
            </div>
            <div class="form-group row mb-2">
                <label for="Beschreibung" class="col-sm-3 col-form-label">Beschreibung:</label>
                <div class="col-sm-9">
                    <input type="text" id="Beschreibung" name="description" maxlength = "50" class="form-control">
                </div>
            </div>
            <button type="submit" class="btn btn-primary d-block mb-2 float-end">Erstellen</button>
        </form>
    </div>
</div>

<#include "footer.ftl">