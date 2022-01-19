<#include "header.ftl">

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
                    <th>Fahrt-ID:</th>
                    <th>Von:</th>
                    <th>Nach:</th>
                  </tr>
                </table>
            </div>
          </div>
        </div>
      </div>
    </div>

<#include "footer.ftl">
