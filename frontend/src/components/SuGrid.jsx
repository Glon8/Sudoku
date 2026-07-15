import React from 'react'

import { Center, Grid } from '@chakra-ui/react'

function SuGrid({ children, ...props }) {
    return (
        <Center bg={'blue'} borderRadius={'xl'} >

            <Grid gap={{base: 1, smToMd: 1, md: 2}} m={{ base: 2, smToMd: 2, md: 3 }}
                w={'min(90vw, 90vh)'} maxW={{ base: 'min(100vw, 100vw)', smToMd: 'min(100vw, 100vw)', md: '30rem' }}
                h={'min(90vw, 90vh)'} maxH={{ base: 'min(100vw, 100vw)', smToMd: 'min(100vw, 100vw)', md: '30rem' }}
                templateRows={"repeat(9, 1fr)"}
                templateColumns={"repeat(9, 1fr)"}
                {...props}>{children}</Grid>

        </Center>
    )
}

export default SuGrid