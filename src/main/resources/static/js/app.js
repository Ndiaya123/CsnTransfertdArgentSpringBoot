

    $('.btnRemove').click(function () {
        //
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-success',
                cancelButton: 'btn btn-danger'
            },
            buttonsStyling: false
        })

        swalWithBootstrapButtons.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'No, cancel!',
            reverseButtons: true
        }).then((result) => {
            if (result.value) {
                var id = $(this).attr('data')
                $.ajax({
                    url: '/admin/remove/' + id,
                    type: 'get',
                    success: function (data) {
                        if(data.status === "success") {
                            // swalWithBootstrapButtons.fire(
                            //     'Deleted!',
                            //     'La ligne a Ã©tÃ© supprimÃ©e avec succÃ¨s.',
                            //     'success'
                            // )
                            window.location.reload()
                        }
                        else {
                            swalWithBootstrapButtons.fire(
                                'Deleted!',
                                'Erreur de suppression.',
                                'error'
                            )
                        }

                    },
                    error: function (error) {
                        console.error(error);
                    }
                })
            } else if (
                /* Read more about handling dismissals below */
                result.dismiss === Swal.DismissReason.cancel
            ) {
                swalWithBootstrapButtons.fire(
                    'Cancelled',
                    'Your imaginary file is safe :)',
                    'error'
                )
            }
        })
        //
    });
