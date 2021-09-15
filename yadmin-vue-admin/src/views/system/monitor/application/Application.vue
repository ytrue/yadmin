<template>
    <div>
        <iframe
            :scrolling="scrolling"
            :src="urlPath"
            class="iframe"
            ref="iframe"
        ></iframe>
    </div>
</template>

<script>
    export default {
        name: "Application",
        data() {
            return {
                scrolling:"auto",
                urlPath: 'http://127.0.0.1:9996/wallboard'
            }
        },
        mounted() {
            this.iframeInit()
        },
        methods: {
            iframeInit() {
                const iframe = this.$refs.iframe
                const clientHeight = document.documentElement.clientHeight - 54
                iframe.style.height = `${clientHeight}px`
            }
        }
    }
</script>

<style>

    body {
        overflow-y: hidden
    }

    .iframe {
        width: 100%;
        height: 100%;
        border: 0;
        overflow: hidden;
        box-sizing: border-box;
    }
</style>
