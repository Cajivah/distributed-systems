<template>

</template>


<script>
    import {formatDate} from "@/utils/dateFormatter";

    export default {
        name: 'the-booking-summary',
        props: {
            value: {
                required: true
            }
        },
        data() {
            return {}
        },
        methods: {
            formatDate,
            closeModal: function () {
                $emit('input', false);
            }
        }
    };
</script>