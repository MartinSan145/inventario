let pagos = [];

let facturaIdActual = null;
let totalFacturaActual = 0;
let totalPagadoActual = 0;
let saldoActual = 0;
let filaFacturaActual = null;

        document.getElementById("btnAgregarPago").addEventListener("click", function() {

            const selectMetodo = document.getElementById("metodoPago");
            const metodoId = selectMetodo.value;
            const metodoTexto = selectMetodo.options[selectMetodo.selectedIndex].text;

            const monto = parseFloat(document.getElementById("montoPago").value) || 0;

            const detalle = document.getElementById("detallePago").value.trim();


            if (metodoId === "") {
                alert("SELECCIONE UN METODO DE PAGO");
                return;
            }

            if (monto <=0) {
                alert("INGRESE UN MONTO VALIDO")
                return;
            }


            const pago = {
                metodoId: metodoId,
                metodoTexto: metodoTexto,
                monto: monto,
                detalle: detalle
            };

            pagos.push(pago);
            console.log("Pagos actuales:", pagos);


            const tabla = document.getElementById("pagosBody");

            const mensaje = document.getElementById("sinPagos");

            if (mensaje) {
                mensaje.style.display = "none";
            }

            const fila = document.createElement("tr");

            fila.innerHTML = `
             <td>${metodoTexto}</td>
             <td>${detalle || "-"}</td>

             <td class="text-end">
                Gs. ${formatearGuaraniesPagos(monto)}
             </td>

            <td class="text-center">
                <button type="button" class="btn btn-sm btn-danger btnEliminarPago">
                <i class="bi bi-trash"></i> </button>
            </td>`;

            tabla.appendChild(fila);

            fila.querySelector(".btnEliminarPago")
            .addEventListener("click", function() {

            const index = Array.from(tabla.children).indexOf(fila);

            pagos.splice(index, 1);


            fila.remove();


            if (tabla.children.length === 0 && mensaje) {
            mensaje.style.display = "table-row";
            }
            actualizarPago();

            });

            document.getElementById("metodoPago").value = "";
            document.getElementById("montoPago").value = 0;
            document.getElementById("detallePago").value = "";

            actualizarPago();


            });


async function abrirModalPago(boton) {

    const facturaId = boton.dataset.id;
     filaFacturaActual = boton.closest("tr");

    try {
        const response = await fetch(`/facturas/${facturaId}/datos-pago`);

        if (!response.ok) {
            throw new Error("Error al obtener el total");
        }

        const total = await response.json();

        console.log("ID factura:", facturaId);
        console.log("Total:", total);

        document.getElementById("facturaIdPago").value = facturaId;
        document.getElementById("totalPagar").dataset.valor = total;
        document.getElementById("totalPagar").textContent = "Gs. " + formatearGuaraniesPagos(total);

        mostrarFechaActual();

        pagos = [];
        document.getElementById("pagosBody").innerHTML = `
            <tr id="sinPagos">
                <td colspan="4"
                    class="text-center text-muted py-4">

                    <i class="bi bi-cash-stack fs-3"></i>

                    <br>

                    No se encontraron pagos

                </td>
            </tr>
        `;

        actualizarPago();

        const modal = new bootstrap.Modal(
            document.getElementById("modalPago")
        );

        modal.show();

    } catch (error) {
        console.error(error);
        alert("No se pudo obtener el total de la factura");
    }
}



function formatearGuaraniesPagos(numero) {

    return new Intl.NumberFormat("es-PY")
        .format(numero);
}
function actualizarPago() {

    const totalFactura = parseFloat(document.getElementById("totalPagar").dataset.valor) || 0;

    let totalPagado = 0;

    pagos.forEach(function(pago) {

        totalPagado += pago.monto;

    });

    const saldo = totalFactura - totalPagado;

    document.getElementById("pagoPagado").textContent = "Gs. " + formatearGuaraniesPagos(totalPagado);

    document.getElementById("pagoSaldo").textContent = "Gs. " + formatearGuaraniesPagos(saldo);

    document.getElementById("saldoPendiente").textContent = "Gs. " + formatearGuaraniesPagos(saldo);

        console.log("//=================================//");
        console.log("TOTAL FACTURA:", totalFactura);
        console.log("TOTAL PAGADO:", totalPagado);
        console.log("SALDO:", saldo);
        console.log("//=================================//");
        }

    function mostrarFechaActual() {

        const fecha = new Date();

        const dia = String(fecha.getDate()).padStart(2, "0");
        const mes = String(fecha.getMonth() + 1).padStart(2, "0");
        const año = fecha.getFullYear();

        document.getElementById("fechaActual").textContent =
            `— Fecha ${dia}/${mes}/${año}`;
    }


    async function guardarPago() {

    if (pagos.length === 0) {
        alert("Debe agregar al menos un medio de pago");
        return;
    }

    const facturaId =
        document.getElementById("facturaIdPago").value;

    if (!facturaId) {
        alert("No se encontró la factura");
        return;
    }

    const detalles = pagos.map(function(pago) {

        return {
            tipoPagoId: Number(pago.metodoId),
            monto: Math.round(pago.monto),
            detalle: pago.detalle
        };

    });

    const datos = {
        facturaId: Number(facturaId),
        detalles: detalles
    };

    console.log("Datos a enviar:", datos);

    try {

        const response = await fetch("/guardar-pago", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(datos)
        });

        const resultado = await response.text();

        if (!response.ok) {
            throw new Error(resultado);
        }

        console.log("Pago guardado:", resultado);
        limpiarFormularioPago();

        if (filaFacturaActual) {
            filaFacturaActual.remove();
            filaFacturaActual = null;
        }


        const modalElement = document.getElementById("modalPago");
        const modal = bootstrap.Modal.getInstance(modalElement);

        if (modal) {
            modal.hide();
        }

        Swal.fire({
            icon: 'success',
            title: '¡Cobrado correctamente!',
            text: 'Los datos fueron guardados con éxito.',
            confirmButtonText: 'Entendido',
            confirmButtonColor: '#198754',
            background: '#ffffff',
            borderRadius: '15px'
        });


    } catch (error) {

        console.error("Error al guardar pago:", error);

        alert(
            "No se pudo guardar el pago:\n" +
            error.message
        );
    }
}

    document.getElementById("btnGuardarPago").addEventListener( "click", function() { guardarPago(); } );

function limpiarFormularioPago() {

    // Limpiar array de pagos
    pagos = [];

    // Limpiar campos
    document.getElementById("metodoPago").value = "";
    document.getElementById("montoPago").value = "";
    document.getElementById("detallePago").value = "";

    // Limpiar tabla de detalles
    const tabla = document.getElementById("pagosBody");

    tabla.innerHTML = `
        <tr id="sinPagos">
            <td colspan="4"
                class="text-center text-muted py-4">

                <i class="bi bi-cash-stack fs-3"></i>

                <br>

                No se encontraron pagos

            </td>
        </tr>
    `;

    // Actualizar resumen
    actualizarPago();
}
function imprimirFactura(boton) {
    const id = boton.getAttribute("data-id");

    window.open('/imprimir/' + id, '_blank');
}