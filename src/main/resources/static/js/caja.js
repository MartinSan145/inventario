
    document.addEventListener("DOMContentLoaded", function () {


        const cantidades =
            document.querySelectorAll(".cantidad-moneda");


        function formatearGs(valor) {

            return "Gs. " +
                Number(valor).toLocaleString("es-PY");

        }


        function calcularCierre() {

            let totalGeneral = 0;


            cantidades.forEach(function (input) {

                const valor =
                    Number(input.dataset.valor);


                const cantidad =
                    Number(input.value) || 0;


                const total =
                    valor * cantidad;


                const fila =
                    input.closest("tr");


                const totalCelda =
                    fila.querySelector(".total-denominacion");


                totalCelda.textContent =
                    formatearGs(total);


                totalGeneral += total;

            });


            document.getElementById("totalCierre")
                .textContent =
                formatearGs(totalGeneral);

        }


        cantidades.forEach(function (input) {

            input.addEventListener(
                "input",
                calcularCierre
            );

        });


        calcularCierre();

    });
