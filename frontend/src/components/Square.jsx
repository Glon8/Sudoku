import React from 'react'
import { Center } from '@chakra-ui/react'

function Square({ children, ...props }) {
    return (
        <Center borderRadius={'md'} bg={'red'} {...props}>{children}</Center>
    )
}

export default Square