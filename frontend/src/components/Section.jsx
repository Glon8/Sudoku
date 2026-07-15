import React from 'react'

import { Center, Grid } from '@chakra-ui/react'

function Section({ rotation, children, ...props }) {
    return (
        <Grid gap={{ base: 1, smToMd: 1, md: 2 }}
            maxW={{ base: 'min(100vw, 100vw)', smToMd: 'min(100vw, 100vw)', md: '45rem' }}
            maxH={{ base: 'min(100vw, 100vw)', smToMd: 'min(100vw, 100vw)', md: '45rem' }}
            templateRows={rotation ? "repeat(9, 1fr)" : "repeat(1, 1fr)"}
            templateColumns={rotation ? "repeat(1, 1fr)" : "repeat(9, 1fr)"}
            {...props}>{children}</Grid>
    )
}

export default Section