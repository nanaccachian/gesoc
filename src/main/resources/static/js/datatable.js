$(document).ready(function () {
  /*
   * Insert a 'details' column to the table
   */
  var nCloneTh = document.createElement("th")
  var nCloneTd = document.createElement("td")

  nCloneTd.className = "center"
  let i = 1

  $("#hidden-table-info thead tr").each(function () {
    this.insertBefore(nCloneTh, this.childNodes[0])
  })

  $("#hidden-table-info tbody tr").each(function () {
    nCloneTd.innerHTML = `<div>${i}</div>`
    i++
    this.insertBefore(nCloneTd.cloneNode(true), this.childNodes[0])
  })

  /*
   * Initialse DataTables, with no sorting on the 'details' column
   */
  var oTable = $("#hidden-table-info").dataTable({
    aoColumnDefs: [
      {
        bSortable: false,
        aTargets: [0],
      },
    ],
    aaSorting: [[1, "asc"]],
  })
})
